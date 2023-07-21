package com.geico.roadsideAssistance.services;

import com.geico.roadsideAssistance.pojo.Assistant;
import com.geico.roadsideAssistance.pojo.Customer;
import com.geico.roadsideAssistance.pojo.Geolocation;
import com.geico.roadsideAssistance.util.ApplicationUtil;
import com.geico.roadsideAssistance.util.DistanceUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoadsideAssistanceServiceImpl implements RoadsideAssistanceService {


    //ApplicationUtil applicationUtil  ;

    public Map<Integer, Assistant> assistantMap;

    public Map<Integer, Integer> assistantCustomerMap = new HashMap<>();

    private static Map<Integer, Assistant> getSortedSetBasedonDistance(Geolocation geolocation) {
        List<Assistant> assistantlst = ApplicationUtil.getAssistant();

        Map<Integer, Assistant> SortedAssistantMap = new TreeMap<>();
        // using TreeMap to sort bases on shortest distance

        for (Assistant assistant : assistantlst) {

            SortedAssistantMap.put(DistanceUtil.getDistance(assistant.getLatitude(), assistant.getLongitude(), geolocation.getLatitude(), geolocation.getLongitude()), assistant);
        }

        // Set<Assistant> valueSet= new HashSet<Assistant>(assistantMap.values());
        return SortedAssistantMap;
    }

    @Override
    public void updateAssistantLocation(Assistant assistant, Geolocation assistantLocation) {

        assistantMap = ApplicationUtil.getAssistantMap();

        Assistant asst = assistantMap.get(assistant.assistantID);
        asst.setLatitude(assistantLocation.getLatitude());
        asst.setLongitude(assistantLocation.getLongitude());

    }

    @Override
    public Set<Assistant> findNearestAssistants(Geolocation geolocation, int limit) {

        Map<Integer, Assistant> assistantMap = getSortedSetBasedonDistance(geolocation);

        Set<Assistant> valueSet = new HashSet<Assistant>(assistantMap.values());

        Set<Assistant> assistantSet  = new HashSet<Assistant>() ;

        int count = 0;
        for (Assistant assistant : valueSet) {

            if (!assistantCustomerMap.containsKey(assistant.getAssistantID())) {

                if (count != limit) {
                    assistantSet.add(assistant);
                    count ++;
                } else {
                    break;
                }
            }

        }


        return assistantSet;
    }

    @Override
    public Optional<Assistant> reserveAssistant(Customer customer, Geolocation customerLocation) {

        Map<Integer, Assistant> assistantMap = getSortedSetBasedonDistance(customerLocation);
        Optional<Assistant> assistant = null;

        for (Map.Entry<Integer, Assistant> entry : assistantMap.entrySet()) {
            assistant = Optional.ofNullable(entry.getValue());


            if (!assistantCustomerMap.containsKey(assistant.get().getAssistantID())) {
                assistantCustomerMap.put(assistant.get().getAssistantID(), customer.customerID);
                break;
            }

        }
        return assistant;

    }

    @Override
    public void releaseAssistant(Customer customer, Assistant assistant) {

        assistantCustomerMap.remove(assistant.getAssistantID());


    }
}

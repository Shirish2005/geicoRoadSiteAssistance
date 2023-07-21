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

    private static SortedSet<Assistant> getSortedSetBasedonDistance(Geolocation geolocation) {
        List<Assistant> assistantlst = ApplicationUtil.getAssistant();

        SortedSet<Assistant> assistantSortedSet =new TreeSet<>(
                Comparator.comparing(Assistant::getDistance));

        // using TreeMap to sort bases on shortest distance

        for (Assistant assistant : assistantlst) {

            assistant.setDistance(DistanceUtil.getDistance(assistant.getLatitude(), assistant.getLongitude(), geolocation.getLatitude(), geolocation.getLongitude()));
            assistantSortedSet.add(assistant);
        }

        // Set<Assistant> valueSet= new HashSet<Assistant>(assistantMap.values());
        return assistantSortedSet;
    }

    @Override
    public void updateAssistantLocation(Assistant assistant, Geolocation assistantLocation) {

        assistantMap = ApplicationUtil.getAssistantMap();

        Assistant asst = assistantMap.get(assistant.assistantID);
        asst.setLatitude(assistantLocation.getLatitude());
        asst.setLongitude(assistantLocation.getLongitude());

    }

    @Override
    public SortedSet<Assistant> findNearestAssistants(Geolocation geolocation, int limit) {

        //SortedSet<Assistant> assistantSortedSet = new TreeSet();


        SortedSet<Assistant> assistantSortedSet = getSortedSetBasedonDistance(geolocation);
        SortedSet<Assistant> assistantSet = new TreeSet<>(
                Comparator.comparing(Assistant::getDistance));

        /*   Set<Assistant> valueSet = new HashSet<Assistant>(assistantMap.values());


         */
        int count = 0;
        for (Assistant assistant : assistantSortedSet) {

            if (!assistantCustomerMap.containsKey(assistant.getAssistantID())) {

                if (count != limit) {
                    assistantSet.add(assistant);
                    count++;
                } else {
                    break;
                }
            }

        }


        return assistantSet;
    }

    @Override
    public Optional<Assistant> reserveAssistant(Customer customer, Geolocation customerLocation) {

        SortedSet<Assistant> assistantSortedSet = getSortedSetBasedonDistance(customerLocation);
        SortedSet<Assistant> assistantSet = new TreeSet<Assistant>();


        // Map<Integer, Assistant> assistantMap = getSortedSetBasedonDistance(customerLocation);
        Optional<Assistant> assistantEmpty = null;

        for (Assistant assistant : assistantSortedSet) {

            if (!assistantCustomerMap.containsKey(assistant.getAssistantID())) {
                assistantCustomerMap.put(assistant.getAssistantID(), customer.customerID);
                return Optional.of(assistant);
            }

        }
        return assistantEmpty;

    }

    @Override
    public void releaseAssistant(Customer customer, Assistant assistant) {

        assistantCustomerMap.remove(assistant.getAssistantID());


    }
}

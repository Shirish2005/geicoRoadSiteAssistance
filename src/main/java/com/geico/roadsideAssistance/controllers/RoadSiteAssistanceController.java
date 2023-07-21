package com.geico.roadsideAssistance.controllers;

import com.geico.roadsideAssistance.pojo.Assistant;
import com.geico.roadsideAssistance.pojo.AssistantLocation;
import com.geico.roadsideAssistance.pojo.Customer;
import com.geico.roadsideAssistance.pojo.Geolocation;
import com.geico.roadsideAssistance.services.RoadsideAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

@RestController
@RequestMapping(value = "/roadsideassistance")
public class RoadSiteAssistanceController {

    @Autowired
    RoadsideAssistanceService roadsideAssistanceService;


    @PutMapping(value = "/updateAssistantLocation")
    public ResponseEntity<HttpStatus> updateAssistantLocation(@RequestBody AssistantLocation assistantLocation) {

        Assistant assistant = new Assistant();
        Geolocation geolocation = new Geolocation();
        assistant.setAssistantID(assistantLocation.getAssistantID());
        geolocation.setLatitude(assistantLocation.getLatitude());
        geolocation.setLongitude(assistantLocation.getLongitude());

        roadsideAssistanceService.updateAssistantLocation(assistant, geolocation);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/findNearestAssistants")
    public ResponseEntity<Set<Assistant>> findNearestAssistants(@RequestBody Geolocation geolocation) {

        Set<Assistant> response = roadsideAssistanceService.findNearestAssistants(geolocation, 5);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping(value = "/reserveAssistant")
    public ResponseEntity<Optional<Assistant>> reserveAssistant(@RequestBody Customer customer) {

        Geolocation geolocation = new Geolocation();
        geolocation.setLatitude(customer.getLatitude());
        geolocation.setLongitude(customer.getLongitude());

        Optional<Assistant> response = roadsideAssistanceService.reserveAssistant(customer , geolocation );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping(value = "/releaseAssistant")
    public ResponseEntity<Optional<Assistant>> releaseAssistant(@RequestBody Assistant assistant) {

        Customer customer = null;

         roadsideAssistanceService.releaseAssistant(customer , assistant );

        return new ResponseEntity<>(HttpStatus.OK);

    }

}

package com.nkentech.covid19tracker.controllers;

import com.nkentech.covid19tracker.models.LocationStats;
import com.nkentech.covid19tracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<LocationStats> home(){
        return coronaVirusDataService.getAllStats();
    }


}

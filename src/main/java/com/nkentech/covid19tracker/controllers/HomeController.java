package com.nkentech.covid19tracker.controllers;

import com.nkentech.covid19tracker.models.LocationStats;
import com.nkentech.covid19tracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HomeController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<LocationStats> home(){
        return coronaVirusDataService.getAllStats();
    }

    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public List<LocationStats>search(@PathVariable(value = "search") String search){
        return coronaVirusDataService.search(search);
    }


}

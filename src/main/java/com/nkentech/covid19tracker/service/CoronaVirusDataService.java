package com.nkentech.covid19tracker.service;

import com.nkentech.covid19tracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoronaVirusDataService {

    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats (){
            return allStats;
    }


    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public List<LocationStats> getStatistics() throws IOException, InterruptedException {
        HttpResponse<String> response = fetchVirusData();
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for(CSVRecord record : records){
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            LocationStats locationStats = new LocationStats(
                    record.get("Province/State"),
                    record.get("Country/Region"),
                    latestCases,
                    latestCases - prevDayCases
            );
           this.allStats.add(locationStats);
        }
        return allStats;
    }

    public List<LocationStats> search(String search) throws Exception{
        List<LocationStats> allSearch = new ArrayList<>();
        ;
        if(allSearch.isEmpty()) {
            LocationStats stats = searchByState(search);
            if(stats != null){
                allSearch.add(stats);
            }
            else{
                allSearch.addAll(searchByCountry(search));
            }
        }
        return allSearch;
    }


    private LocationStats searchByState(String search){
        LocationStats stats = null;
            for(LocationStats s : getAllStats()){
                if(s.getState().equalsIgnoreCase(search)){
                    stats = s;
                }
            }
         return stats;
    }

    private List<LocationStats> searchByCountry(String search){
        return this.getAllStats().stream()
                .filter(locationStats -> locationStats.getCountry().equalsIgnoreCase(search))
                .collect(Collectors.toList());
    }

    private HttpResponse<String> fetchVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }



}

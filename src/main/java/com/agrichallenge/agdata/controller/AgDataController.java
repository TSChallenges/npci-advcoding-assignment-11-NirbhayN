package com.agrichallenge.agdata.controller;

import com.agrichallenge.agdata.model.AgData;
import com.agrichallenge.agdata.service.AgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/agdata")
public class AgDataController {

    @Autowired
    private AgDataService agDataService;

    @GetMapping("")
    public ResponseEntity<List<AgData>> getAllProducts() throws IOException {
        List<AgData> products = agDataService.loadAgData();
        if (products.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // TODO: GET /api/agdata/crop-count?cropName=corn

    @GetMapping("crop-count")
    public ResponseEntity<Long> getCropCount(@RequestParam() String cropName){

        return new ResponseEntity<>(agDataService.getCropCount(cropName),HttpStatus.OK);

    }


    // TODO: GET /api/agdata/average-yield?cropName=wheat

    @GetMapping("average-yield")

    public ResponseEntity<Double> getAverageYeild(@RequestParam String cropName){

    Double avg=agDataService.getAverageYield(cropName);
    if (avg==0.0)return new ResponseEntity(0.0,HttpStatus.NO_CONTENT);

    return new ResponseEntity<>(avg,HttpStatus.OK);
    }

    // TODO: GET /api/agdata/by-region?region=Midwest
    @GetMapping("by-region")
    public ResponseEntity<List<AgData>> getByRegion(@RequestParam String region){

        List<AgData> dataByRegion=agDataService.getRecordsByRegion(region);
        if (dataByRegion.size()>0)return new ResponseEntity<>(dataByRegion,HttpStatus.OK);
        else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }


}

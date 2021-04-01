package thefaco.beverage.controller;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.domain.BeverageLocation;
import thefaco.beverage.service.BeverageLocationService;
import thefaco.beverage.service.BeverageService;

import java.io.OutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RaspberryPiController {

    private final BeverageLocationService beverageLocationService;
    private final BeverageService beverageService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/beverageInfoByJson/raspberry-pi", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Beverage> beverageInfoByJson() throws JsonProcessingException {
        List<Beverage> beverages = beverageService.findBeverages();

        log.info("beverageInfoByJson/raspberry-pi access={}", beverages);

        return beverages;
    }

    @PostMapping(value = "/beverageLocationInfoByJson/raspberry-pi", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BeverageLocation> beverageLocationInfoByJson() throws JsonProcessingException {

        List<BeverageLocation> beverageLocations = beverageLocationService.findBeverageLocations();

        log.info("beverageLocationInfoByJson/raspberry-pi access={}", beverageLocations);

        return beverageLocations;
    }
}

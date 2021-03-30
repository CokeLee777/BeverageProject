package thefaco.beverage.controller;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import thefaco.beverage.domain.BeverageLocation;
import thefaco.beverage.service.BeverageLocationService;
import thefaco.beverage.service.BeverageService;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RaspberryPiController {

    private final BeverageLocationService beverageLocationService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @ResponseBody
    @PostMapping(value = "/raspberry-pi", produces = MediaType.APPLICATION_JSON_VALUE)
    public String raspberryPi() throws JsonProcessingException {
        String result = "";

        List<BeverageLocation> beverageLocations = beverageLocationService.findBeverageLocations();

        result = objectMapper.writeValueAsString(beverageLocations);

        log.info("raspberry-pi access={}", result);

        return result;
    }
}

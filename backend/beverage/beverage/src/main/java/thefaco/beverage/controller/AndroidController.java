package thefaco.beverage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.service.BeverageLocationService;
import thefaco.beverage.service.BeverageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AndroidController {

    private final BeverageService beverageService;
    private final BeverageLocationService beverageLocationService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/beverageInfoByString/android")
    public String beverageInfoByString(@RequestParam("beverageName") String beverageName) {
        String result = null;

        List<Beverage> findBeverages = beverageService.findBeveragesByName(beverageName);
        String findLocation = beverageLocationService.findByName(beverageName);

        //DB에서 찾은 음료가 있으면
        if(!findBeverages.isEmpty()){
            for(Beverage findbeverage: findBeverages){
                result = findbeverage.toString();
                result += " 위치는 " + findLocation + "에 위치합니다.";
            }
        } else {
            result = "찾으시는 음료가 없습니다.";
        }

        log.info("android access={}", result);

        return result;
    }

    @PostMapping(value = "/beverageInfoByJson/android", produces = MediaType.APPLICATION_JSON_VALUE)
    public Beverage beverageInfoByJson(@RequestParam("beverageName") String beverageName) throws JsonProcessingException {
        List<Beverage> findBeverages = beverageService.findBeveragesByName(beverageName);

        //DB에서 찾은 음료가 있으면
        if(!findBeverages.isEmpty()){
            return findBeverages.get(0);
        } else {
            return null;
        }
    }

//    @PostMapping(value = "/beverageLocationInfoByJson/android", produces = MediaType.APPLICATION_JSON_VALUE)
//    public BeverageLocationJsonForm beverageLocationInfoByJson(@RequestParam("beverageLocationName") String beverageLocationName) throws JsonProcessingException {
//        BeverageLocationJsonForm beverageLocationJsonForm = beverageLocationService.findObjectByName(beverageLocationName);
//
//        //log.info("row={} column={}", beverageLocationJsonForm.getRow(), beverageLocationJsonForm.getColumn());
//
//        return beverageLocationJsonForm;
//    }

}

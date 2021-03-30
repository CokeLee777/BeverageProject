package thefaco.beverage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.service.BeverageLocationService;
import thefaco.beverage.service.BeverageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AndroidController {

    private final BeverageService beverageService;
    private final BeverageLocationService beverageLocationService;

    @ResponseBody
    @PostMapping("/findBeverageInfo")
    public String android(@RequestParam("beverageName") String beverageName) {
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

    @ResponseBody
    @PostMapping("/json-response")
    public String test(@RequestParam("beverageName") String beverageName) throws JsonProcessingException {

        String result = null;
        ObjectMapper objectMapper = new ObjectMapper();

        List<Beverage> findBeverages = beverageService.findBeveragesByName(beverageName);

        //DB에서 찾은 음료가 있으면
        if(!findBeverages.isEmpty()){
            for(Beverage findbeverage: findBeverages){
                result = objectMapper.writeValueAsString(findbeverage);
            }
        } else {
            result = "찾으시는 음료가 없습니다.";
        }

        return result;
    }

}

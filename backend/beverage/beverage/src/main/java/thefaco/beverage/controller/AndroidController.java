package thefaco.beverage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.service.BeverageLocationService;
import thefaco.beverage.service.BeverageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AndroidController {

    private final BeverageService beverageService;
    private final BeverageLocationService beverageLocationService;

    @RequestMapping(method = RequestMethod.POST, value="/findBeverageInfo")
    public @ResponseBody String android(HttpServletRequest httpServletRequest) {
        String result = null;
        String param1 = httpServletRequest.getParameter("param1");

        List<Beverage> findBeverages = beverageService.findBeveragesByName(param1);
        String findLocation = beverageLocationService.findByName(param1);

        //DB에서 찾은 음료가 있으면
        if(!findBeverages.isEmpty()){
            for(Beverage findbeverage: findBeverages){
                result = findbeverage.toString();
                result += " 위치는 " + findLocation + "에 위치합니다.";
            }
        } else {
            result = "찾으시는 음료가 없습니다.";
        }

        return result;
    }

}

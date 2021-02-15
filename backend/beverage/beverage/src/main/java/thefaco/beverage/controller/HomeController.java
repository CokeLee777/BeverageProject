package thefaco.beverage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j  //로그를 찍어주는 어노테이션
public class HomeController {

    @RequestMapping("/")
    public String Home(){
        log.info("Home access");
        return "home";
    }
}

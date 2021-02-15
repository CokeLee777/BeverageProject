package thefaco.beverage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.service.BeverageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BeverageController {

    private final BeverageService beverageService;

    @GetMapping("/beverages/new")
    public String createForm(Model model){
        model.addAttribute("form", new BeverageForm());
        return "beverages/createBeverageForm";
    }

    @PostMapping("/beverages/new")
    public String create(BeverageForm form){

        Beverage beverage = new Beverage();
        beverage.setName(form.getName());
        beverage.setPrice(form.getPrice());
        beverage.setSize(form.getSize());
        beverage.setType(form.getType());

        beverageService.save(beverage);
        return "redirect:/";
    }

    //음료 목록 조회
    @GetMapping("/beverages")
    public String list(Model model){
        List<Beverage> beverages = beverageService.findBeverages();
        model.addAttribute("beverages", beverages);
        return "beverages/beverageList";
    }

    //음료 수정 폼
    @GetMapping("/beverages/{beverageId}/edit")
    public String updateBeverageForm(@PathVariable("beverageId") Long beverageId, Model model){
        Beverage beverage = beverageService.findOne(beverageId);

        BeverageForm form = new BeverageForm();
        form.setId(beverage.getId());
        form.setName(beverage.getName());
        form.setPrice(beverage.getPrice());
        form.setSize(beverage.getSize());
        form.setType(beverage.getType());

        model.addAttribute("form", form);
        return "beverages/updateBeverageForm";
    }

    //음료 수정
    @PostMapping("/beverages/{beverageId}/edit")
    public String updateBeverage(@PathVariable String beverageId, @ModelAttribute("form") BeverageForm form){

        Beverage beverage = new Beverage();
        beverage.setId(form.getId());
        beverage.setName(form.getName());
        beverage.setPrice(form.getPrice());
        beverage.setType(form.getType());
        beverage.setSize(form.getSize());

        beverageService.save(beverage);
        return "redirect:/beverages";
    }
}

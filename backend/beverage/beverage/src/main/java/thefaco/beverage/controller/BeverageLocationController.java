package thefaco.beverage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.domain.BeverageLocation;
import thefaco.beverage.service.BeverageLocationService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BeverageLocationController {

    private final BeverageLocationService beverageLocationService;

    @GetMapping("/beverageLocations/new")
    public String createLocationForm(Model model){
        model.addAttribute("form", new BeverageLocationForm());
        return "beverageLocations/createBeverageLocationForm";
    }

    @PostMapping("/beverageLocations/new")
    public String createLocation(BeverageLocationForm form){

        BeverageLocation beverageLocation = new BeverageLocation();
        beverageLocation.setC1(form.getC1());
        beverageLocation.setC2(form.getC2());
        beverageLocation.setC3(form.getC3());
        beverageLocation.setC4(form.getC4());

        beverageLocationService.save(beverageLocation);
        return "redirect:/";
    }

    //음료위치 목록 조회
    @GetMapping("/beverageLocations")
    public String list(Model model){
        List<BeverageLocation> beverageLocations = beverageLocationService.findBeverageLocations();
        model.addAttribute("beverageLocations", beverageLocations);
        return "beverageLocations/beverageLocationList";
    }

    //음료위치 수정 폼
    @GetMapping("/beverageLocations/{beverageLocationId}/edit")
    public String updateBeverageLocationForm(@PathVariable("beverageLocationId") Long beverageLocationId, Model model){
        BeverageLocation beverageLocation = beverageLocationService.findOne(beverageLocationId);

        BeverageLocationForm form = new BeverageLocationForm();
        form.setId(beverageLocation.getId());
        form.setC1(beverageLocation.getC1());
        form.setC2(beverageLocation.getC2());
        form.setC3(beverageLocation.getC3());
        form.setC4(beverageLocation.getC4());

        model.addAttribute("form", form);
        return "beverageLocations/updateBeverageLocationForm";
    }

    //음료위치 수정
    @PostMapping("/beverageLocations/{beverageLocationId}/edit")
    public String updateBeverageLocation(@PathVariable String beverageLocationId, @ModelAttribute("form") BeverageLocationForm form){

        BeverageLocation beverageLocation = new BeverageLocation();
        beverageLocation.setId(form.getId());
        beverageLocation.setC1(form.getC1());
        beverageLocation.setC2(form.getC2());
        beverageLocation.setC3(form.getC3());
        beverageLocation.setC4(form.getC4());

        beverageLocationService.save(beverageLocation);
        return "redirect:/beverageLocations";
    }
}

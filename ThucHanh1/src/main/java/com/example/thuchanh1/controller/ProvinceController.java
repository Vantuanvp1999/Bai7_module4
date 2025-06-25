package com.example.thuchanh1.controller;

import com.example.thuchanh1.model.Customer;
import com.example.thuchanh1.model.Province;
import com.example.thuchanh1.service.ICustomerService;
import com.example.thuchanh1.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView listProvince(){
        ModelAndView modelAndView = new ModelAndView("/province/list");
        Iterable<Province> provinces= provinceService.findAll();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createProvince(){
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes){
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message","create new province successfully");
        return new ModelAndView("redirect:/provinces");
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateProvince(@PathVariable("id") long id){
        Optional<Province> province = provinceService.findById(id);
        if(province.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/province/update");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        }else{
            return new ModelAndView("/error_404");
        }

    }
    @PostMapping("/update/{id}")
    public ModelAndView update(@ModelAttribute Province province, RedirectAttributes redirectAttributes ){
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message","update province successfully");
        return new ModelAndView("redirect:/provinces");
    }
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") long id){
        Optional<Province> provinceOptional =provinceService.findById(id);
        if(!provinceOptional.isPresent()){
            return new ModelAndView("/error_404");
        }
        Iterable<Customer> customers = customerService.findAllByProvice(provinceOptional.get());
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}

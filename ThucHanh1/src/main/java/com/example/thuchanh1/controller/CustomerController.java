package com.example.thuchanh1.controller;

import com.example.thuchanh1.model.Customer;
import com.example.thuchanh1.service.ICustomerService;
import com.example.thuchanh1.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;
    @GetMapping
    public ModelAndView listCustomer(@PageableDefault(value = 2) Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView listCustomerSearch(@RequestParam("search") Optional<String> search,@PageableDefault(value = 2) Pageable pageable) {
       Page<Customer> customers;

        if (search.isPresent()) {
        customers = customerService.findAllByFirstNameContaining(pageable, search.get());
       }else{
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("provinces", provinceService.findAll());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Create new customer successfully");
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("provinces", provinceService.findAll());
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        }else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/update/{id}")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Update customer successfully");
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable long id, RedirectAttributes redirectAttributes) {
        customerService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Delete customer successfully");
        return new ModelAndView("redirect:/customers");
    }
}

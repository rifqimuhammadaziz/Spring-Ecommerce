package rifqimuhammadaziz.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.model.City;
import rifqimuhammadaziz.Library.model.Customer;
import rifqimuhammadaziz.Library.service.contract.CityService;
import rifqimuhammadaziz.Library.service.contract.CustomerService;

import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CityService cityService;

    @GetMapping("/account")
    public String accountHome(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        Customer customer = customerService.findByUsername(principal.getName());
        model.addAttribute("customer", customer);

        return "account";
    }

    @RequestMapping(value = "/update-account", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(@ModelAttribute("customer") Customer customer,
                                 Model model,
                                 RedirectAttributes redirectAttributes,
                                 Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Customer updateCustomer = customerService.update(customer);
        redirectAttributes.addFlashAttribute("customer", updateCustomer);

        return "redirect:/account";
    }
}

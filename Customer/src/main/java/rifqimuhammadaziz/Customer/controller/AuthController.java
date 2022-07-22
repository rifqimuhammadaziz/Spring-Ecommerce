package rifqimuhammadaziz.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.CustomerDto;
import rifqimuhammadaziz.Library.model.Customer;
import rifqimuhammadaziz.Library.service.contract.CustomerService;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "register";
            }

            Customer customer = customerService.findByUsername(customerDto.getUsername());
            if (customer != null) {
                model.addAttribute("error", "Username has been registered, please use another username");
                model.addAttribute("customerDto", customerDto);
            } else {
                if (customerDto.getPassword().equals(customerDto.getRetypePassword())) {
                    customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                    customerService.save(customerDto);
                } else {
                    model.addAttribute("password", "Password is invalid, please check retype password again");
                    model.addAttribute("customerDto", customerDto);
                }
            }

        } catch (Exception e) {
            model.addAttribute("error", "Server Error");
            model.addAttribute("customerDto", customerDto);
            e.printStackTrace();
        }
        return "register";
    }
}

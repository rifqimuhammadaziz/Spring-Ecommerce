package rifqimuhammadaziz.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rifqimuhammadaziz.Library.model.Customer;
import rifqimuhammadaziz.Library.model.Order;
import rifqimuhammadaziz.Library.model.ShoppingCart;
import rifqimuhammadaziz.Library.service.contract.CustomerService;
import rifqimuhammadaziz.Library.service.contract.OrderService;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        Customer customer = customerService.findByUsername(principal.getName());
        if (customer.getPhoneNumber() == null && customer.getAddress() == null && customer.getCity() == null && customer.getCountry() == null) {
            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must complete the profile before checkout!");
            return "account";
        } else {
            model.addAttribute("customer", customer);
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("cart", cart);
        }
        return "checkout";
    }

    @GetMapping("/order")
    public String order(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        Customer customer = customerService.findByUsername(principal.getName());
        List<Order> orderList = customer.getOrders();
        model.addAttribute("orders", orderList);
        return "order";
    }

    @GetMapping("/save-order")
    public String saveOrder(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        Customer customer = customerService.findByUsername(principal.getName());
        ShoppingCart cart = customer.getShoppingCart();
        orderService.saveOrder(cart);

        return "redirect:/order";
    }
}

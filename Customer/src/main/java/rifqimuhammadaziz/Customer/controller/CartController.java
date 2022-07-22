package rifqimuhammadaziz.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rifqimuhammadaziz.Library.model.Customer;
import rifqimuhammadaziz.Library.model.Product;
import rifqimuhammadaziz.Library.model.ShoppingCart;
import rifqimuhammadaziz.Library.repository.ShoppingCartRepository;
import rifqimuhammadaziz.Library.service.contract.CustomerService;
import rifqimuhammadaziz.Library.service.contract.ProductService;
import rifqimuhammadaziz.Library.service.contract.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        Customer customer = customerService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = customer.getShoppingCart();
        model.addAttribute("shoppingCart", shoppingCart);
        if (shoppingCart == null) {
            model.addAttribute("check", "No item in your cart");
        }

        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(@RequestParam("id") Long productId,
                                @RequestParam(value = "id", required = false, defaultValue = "1") int quantity,
                                Principal principal,
                                Model model,
                                HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/login";
        }

        Product product = productService.getProductById(productId);
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart cart = shoppingCartService.addItemToCart(product, quantity, customer);

        return "redirect:" + request.getHeader("Referer");
    }


}

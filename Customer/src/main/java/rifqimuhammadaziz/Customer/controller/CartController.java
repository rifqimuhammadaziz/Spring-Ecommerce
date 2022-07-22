package rifqimuhammadaziz.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/update-cart", params = "action=update", method = RequestMethod.POST)
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Model model,
                             Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = shoppingCartService.updateItemInCart(product, quantity, customer);

            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }
    }

    @RequestMapping(value = "/update-cart", params = "action=delete", method = RequestMethod.POST)
    public String deleteItemFromCart(@RequestParam("id") Long productId,
                                     Model model,
                                     Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = shoppingCartService.deleteItemFromCart(product, customer);

            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }
    }


}

package rifqimuhammadaziz.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rifqimuhammadaziz.Library.model.Product;
import rifqimuhammadaziz.Library.service.contract.ProductService;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Product> listViewProducts = productService.listViewProducts();
        model.addAttribute("products", products);
        model.addAttribute("viewProducts", listViewProducts);
        return "shop";
    }
}

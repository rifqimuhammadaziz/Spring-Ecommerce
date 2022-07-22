package rifqimuhammadaziz.Customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rifqimuhammadaziz.Library.dto.ProductDto;
import rifqimuhammadaziz.Library.model.Category;
import rifqimuhammadaziz.Library.model.Product;
import rifqimuhammadaziz.Library.service.contract.CategoryService;
import rifqimuhammadaziz.Library.service.contract.ProductService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            session.setAttribute("username", principal.getName());
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/home")
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtoList = productService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productDtoList);
        return "index";
    }
}

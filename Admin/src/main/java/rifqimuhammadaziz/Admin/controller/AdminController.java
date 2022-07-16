package rifqimuhammadaziz.Admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rifqimuhammadaziz.Library.service.contract.CategoryService;

@Controller
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    private String categories(Model model) {
        model.addAttribute("title", "Category");
        return "categories";
    }
}

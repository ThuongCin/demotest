package com.Controller;

import com.Entity.Product;
import com.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class HomeController {
    @Autowired
    private com.Service.ProductService ProductService;
    @GetMapping("/")
    public String home(Model model) {
        List<Product> giays = ProductService.getAll();
        model.addAttribute("giays", giays);
        return "index";
        return "index";
        return "index";
        return "index";
        return "index";'
        return "index";
        
    }
    @GetMapping("/gioithieu")
    public String about() {return "about"; }
}

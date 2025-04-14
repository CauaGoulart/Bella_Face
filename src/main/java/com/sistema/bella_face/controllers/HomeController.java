package com.sistema.bella_face.controllers;

import com.sistema.bella_face.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    private final ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("produtos", productRepository.findAll());
        return "home";
    }
}


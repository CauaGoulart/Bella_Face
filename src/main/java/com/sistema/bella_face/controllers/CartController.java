package com.sistema.bella_face.controllers;

import com.sistema.bella_face.entities.Cart;
import com.sistema.bella_face.entities.Product;
import com.sistema.bella_face.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ProductRepository productRepository;

    @PostMapping("/add")
    public String adicionarAoCarrinho(@RequestParam("produtoId") Integer produtoId,
                                      @RequestParam("quantidade") int quantidade,
                                      HttpSession session,
                                      RedirectAttributes redirectAttributes) {

        Cart carrinho = (Cart) session.getAttribute("cart");
        if (carrinho == null) {
            carrinho = new Cart();
        }

        Optional<Product> produtoOpt = productRepository.findById(produtoId);
        if (produtoOpt.isPresent()) {
            boolean adicionado = carrinho.adicionarProduto(produtoOpt.get(), quantidade);
            if (!adicionado) {
                redirectAttributes.addFlashAttribute("erro", "Produto já foi adicionado ao carrinho.");
            } else {
                redirectAttributes.addFlashAttribute("sucesso", "Produto adicionado ao carrinho!");
            }
            session.setAttribute("cart", carrinho);
        } else {
            redirectAttributes.addFlashAttribute("erro", "Produto não encontrado.");
        }

        return "redirect:/home";
    }

    @GetMapping
    public String visualizarCarrinho(Model model, HttpSession session) {
        Cart carrinho = (Cart) session.getAttribute("cart");
        if (carrinho == null) {
            carrinho = new Cart();
        }

        model.addAttribute("itens", carrinho.getItens());
        model.addAttribute("total", carrinho.getTotal());
        return "cart";
    }

    @PostMapping("/remove")
    public String removerItem(@RequestParam("produtoId") Integer produtoId,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Cart carrinho = (Cart) session.getAttribute("cart");
        if (carrinho != null) {
            carrinho.removerProduto(produtoId);
            session.setAttribute("cart", carrinho);
            redirectAttributes.addFlashAttribute("sucesso", "Produto removido do carrinho.");
        }

        return "redirect:/cart";
    }
}

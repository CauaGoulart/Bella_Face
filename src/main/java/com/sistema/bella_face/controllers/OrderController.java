package com.sistema.bella_face.controllers;

import com.sistema.bella_face.entities.Cart;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @PostMapping("/order/checkout")
    public String checkout(HttpSession session, RedirectAttributes redirectAttributes) {
        LocalDate hoje = LocalDate.now();
        DayOfWeek diaSemana = hoje.getDayOfWeek();

        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            redirectAttributes.addFlashAttribute("erro", "Pedidos não podem ser feitos nos finais de semana.");
            return "redirect:/home";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItens().isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Seu carrinho está vazio.");
            return "redirect:/home";
        }

        cart.limpar();
        session.setAttribute("cart", cart);
        redirectAttributes.addFlashAttribute("sucesso", "Pedido finalizado com sucesso!");
        return "redirect:/home";
    }
}

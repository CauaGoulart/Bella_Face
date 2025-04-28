package com.sistema.bella_face.controllers;

import com.sistema.bella_face.entities.Cart;
import com.sistema.bella_face.entities.CartItem;
import com.sistema.bella_face.entities.Customer;
import com.sistema.bella_face.entities.Order;
import com.sistema.bella_face.entities.OrderProduct;
import com.sistema.bella_face.repositories.CustomerRepository;
import com.sistema.bella_face.repositories.OrderRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/order/checkout")
    public String checkout(HttpSession session,
                           RedirectAttributes redirectAttributes,
                           Authentication authentication) {

        LocalDateTime agora = LocalDateTime.now();
        DayOfWeek diaSemana = agora.getDayOfWeek();

        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            redirectAttributes.addFlashAttribute("erro", "Pedidos não podem ser feitos nos finais de semana.");
            return "redirect:/home";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItens().isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Seu carrinho está vazio.");
            return "redirect:/home";
        }

        String username = authentication.getName();
        Optional<Customer> optionalCustomer = customerRepository.findByLogin(username);

        if (optionalCustomer.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Cliente não encontrado.");
            return "redirect:/home";
        }

        Customer customer = optionalCustomer.get();

        Order order = Order.builder()
                .customer(customer)
                .totalOrder(cart.getTotal())
                .createdAt(agora)
                .comments(cart.getComentario() != null ? cart.getComentario() : "")
                .build();

        List<OrderProduct> itens = cart.getItens().stream()
                .map(item -> OrderProduct.builder()
                        .order(order)
                        .product(item.getProduct())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getProduct().getPrice())
                        .totalPrice(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .createdAt(agora)
                        .build())
                .collect(Collectors.toList());

        order.setItems(itens);

        orderRepository.save(order);

        cart.limpar();
        session.setAttribute("cart", cart);

        redirectAttributes.addFlashAttribute("sucesso", "Pedido finalizado com sucesso!");
        return "redirect:/home";
    }
}
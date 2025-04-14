package com.sistema.bella_face.entities;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;

@Getter
public class Cart {
    private final Map<Integer, CartItem> itens = new LinkedHashMap<>();

    public boolean adicionarProduto(Product produto, int quantidade) {
        if (itens.containsKey(produto.getId())) {
            return false;
        }
        itens.put(produto.getId(), new CartItem(produto, quantidade));
        return true;
    }


    public void removerProduto(Integer produtoId) {
        itens.remove(produtoId);
    }

    public void limpar() {
        itens.clear();
    }

    public BigDecimal getTotal() {
        return itens.values().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Collection<CartItem> getItens() {
        return itens.values();
    }
}
package com.wardy.wardy.shoppingcart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException  extends RuntimeException {
    public ShoppingCartNotFoundException(String id) {
        super("Could not find cart with id: " + id);
    }
}

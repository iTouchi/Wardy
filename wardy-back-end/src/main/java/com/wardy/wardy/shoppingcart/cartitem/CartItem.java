package com.wardy.wardy.shoppingcart.cartitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wardy.wardy.product.Product;
import com.wardy.wardy.shoppingcart.ShoppingCart;
import com.wardy.wardy.user.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String key;
    int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ShoppingCart shoppingcart;



}

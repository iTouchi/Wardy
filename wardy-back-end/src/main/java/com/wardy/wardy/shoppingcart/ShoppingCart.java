package com.wardy.wardy.shoppingcart;


import com.wardy.wardy.shoppingcart.cartitem.CartItem;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    Date dateCreated;

    @OneToMany(mappedBy="shoppingcart")
    private List<CartItem> items;


    public ShoppingCart() {
    }

    public ShoppingCart(Date date){
        this.dateCreated = date;
    }


}

package com.wardy.wardy.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.wardy.wardy.user.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String key;

    @Size(min = 3, message = "Name should have at least 3 characters")
    String title;

    double price;
    String category;
    String imageUrl;

    // A product can only be assigned to One User
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


    public Product(String title, double price, String category, String imageUrl) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    protected Product() {
    }

}

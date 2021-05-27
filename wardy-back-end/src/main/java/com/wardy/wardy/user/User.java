package com.wardy.wardy.user;

import com.wardy.wardy.product.Product;
import com.wardy.wardy.task.Task;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;
    String password;
    @Size(min=3, message="Name should have atleast 3 characters")
    private String name;
    @Past
    private Date birthDate;

    private Boolean isAdmin;

    @OneToMany(mappedBy="user")
    private List<Product> products;

    @OneToMany(mappedBy="user")
    private List<Task> tasks;


    public User() { }

    public User(String password,String name, Date birthDate, Boolean isAdmin) {
        super();
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.isAdmin = isAdmin;
    }

}

package com.wardy.wardy.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wardy.wardy.user.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String key;

    @Size(min = 3, message = "Name should have at least 3 characters")
    String title;

    String description;

    // A task can only be assigned to One User
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

     protected  Task(){
        
     }

}

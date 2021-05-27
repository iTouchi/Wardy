package com.wardy.wardy;

import com.wardy.wardy.product.Product;
import com.wardy.wardy.product.ProductRepository;
import com.wardy.wardy.product.category.Category;
import com.wardy.wardy.product.category.CategoryRepository;
import com.wardy.wardy.shoppingcart.ShoppingCart;
import com.wardy.wardy.shoppingcart.ShoppingCartRepository;
import com.wardy.wardy.shoppingcart.cartitem.ItemRepository;
import com.wardy.wardy.user.User;
import com.wardy.wardy.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.wardy.wardy.task.Task;
import com.wardy.wardy.task.TaskRepository;

import java.util.Date;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ProductRepository productRepository,
                                   CategoryRepository categoryRepository,
                                   ShoppingCartRepository shoppingCartRepository,
                                   ItemRepository itemRepository,
                                   TaskRepository taskRepository) {
        return args -> {
            // Create Users
            log.info("Preloading " + userRepository.save(new User("welkom01","Batman",new Date(), true)));
            log.info("Preloading " + userRepository.save(new User("welkom01","Robin",new Date(), false)));

            // Create Products
            log.info("Preloading " + productRepository.save(new Product("Kirby", 30,"Games","https://resources.budgetgaming.nl/boxarts/120200_120200_Kirbystaralliesswitchkopen.jpg")));
            log.info("Preloading " + productRepository.save(new Product("Zelda", 30,"Games","https://images-na.ssl-images-amazon.com/images/I/71u6nI9eAcL._UL500_.jpg")));

            // Create Categories
            log.info("Preloading " + categoryRepository.save(new Category("Games")));
            log.info("Preloading " + categoryRepository.save(new Category("Electronics")));
            log.info("Preloading " + categoryRepository.save(new Category("Toys")));
            log.info("Preloading " + categoryRepository.save(new Category("Board Games")));
            log.info("Preloading " + categoryRepository.save(new Category("Room")));
            log.info("Preloading " + categoryRepository.save(new Category("Food")));

            // Create Shopping-cart
            log.info("Preloading " + shoppingCartRepository.save(new ShoppingCart(new Date())));

            // Create Tasks
            log.info("Preloading " + taskRepository.save(new Task("Brush Teeth","Brushing your teeth before going to bed")));
        };
    }
}
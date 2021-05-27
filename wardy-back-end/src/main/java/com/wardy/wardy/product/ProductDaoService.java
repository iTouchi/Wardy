package com.wardy.wardy.product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ProductDaoService {
    private static List<Product> products = new ArrayList<>();

    private static int productCount = 3;

    static {
        products.add(new Product(
                "Animal Crossing: New Horizons",
                50, "Games",
                "www.google.com"));
        products.add(new Product(
                "30 Seconds Junior",
                24.99, "Board Games",
                "www.google.com"));
        products.add(new Product(
                "NERF Mega Darts",
                13.49, "Toys",
                "www.google.com"));
    }


    public List<Product> getAll() {
        return products;
    }

    public Product create(Product product) {
        if (product.getKey() == null ) product.setKey("setRandomstringhere");

        products.add(product);
        return product;
    }

    public Product get(String id) {
        for (Product p : products) {
            if (p.getKey()==id) return p;
        }
        return null;
    }

    public Product delete(String id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product p = iterator.next();
            if (p.getKey()==id) {
                iterator.remove();
                return p;
            }
        }
            return null;
    }

}

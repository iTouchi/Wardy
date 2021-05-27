package com.wardy.wardy.shoppingcart.cartitem;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<CartItem, String> {
}

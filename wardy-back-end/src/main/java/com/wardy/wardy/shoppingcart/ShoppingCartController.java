package com.wardy.wardy.shoppingcart;

import com.wardy.wardy.shoppingcart.cartitem.CartItem;
import com.wardy.wardy.shoppingcart.cartitem.ItemRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingCartController {

    private final ShoppingCartRepository repo;
    private final ShoppingCartModelAssembler assembler;
    private final ItemRepository itemRepo;

    public ShoppingCartController(ShoppingCartRepository repo, ShoppingCartModelAssembler assembler, ItemRepository itemRepo) {
        this.repo = repo;
        this.assembler = assembler;
        this.itemRepo = itemRepo;
    }

    // Get all carts
    @GetMapping("/carts")
    CollectionModel<EntityModel<ShoppingCart>> all() {

        List<EntityModel<ShoppingCart>> carts = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(carts,
                linkTo(methodOn(ShoppingCartController.class).all()).withSelfRel());
    }

    @PostMapping("/carts")
    ResponseEntity<?> newCart(@RequestBody ShoppingCart newCart) {
        EntityModel<ShoppingCart> entityModel = assembler.toModel(repo.save(newCart));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/carts/{id}")
    EntityModel<ShoppingCart> one(@PathVariable String id) {

        ShoppingCart cart = repo.findById(id)
                .orElseThrow(() -> new ShoppingCartNotFoundException(id));

        return assembler.toModel(cart);
    }

    // Get Items form Cart
    @GetMapping("/carts/{id}/items")
    List<CartItem> all(@PathVariable String id) {
        Optional<ShoppingCart> cartOptional = repo.findById(id);
        return cartOptional.get().getItems();
    }

    // Create new Item in Cart
    @PostMapping("/carts/{id}/items")
    ResponseEntity<Object> createItem(@PathVariable String id, @RequestBody CartItem cartItem) {
        Optional<ShoppingCart> shoppingCartOptional = repo.findById(id);


        ShoppingCart shoppingCart = shoppingCartOptional.get();

        cartItem.setShoppingcart(shoppingCart);
        itemRepo.save(cartItem);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cartItem.getKey())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}


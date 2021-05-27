package com.wardy.wardy.product;


import com.wardy.wardy.user.UserNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;


import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {


    private final ProductRepository repo;
    private final ProductModelAssembler assembler;

    public ProductController(ProductRepository repo, ProductModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    // Get all products
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products")
    CollectionModel<EntityModel<Product>> all() {

        List<EntityModel<Product>> products = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(products,
                linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    // Create new Product
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/products")
    ResponseEntity<?> newProduct(@RequestBody Product newProduct) {
        EntityModel<Product> entityModel = assembler.toModel(repo.save(newProduct));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Get one Product by id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/{id}")
    EntityModel<Product> one(@PathVariable String id) {

        Product product = repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return assembler.toModel(product);
    }

    // Update Product by id
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/products/{id}")
    ResponseEntity<?> replaceUser(@RequestBody Product newProduct, @PathVariable String id) {

        Product updatedProduct = repo.findById(id)
                .map(product -> {
                    product.setUser(newProduct.getUser());
                    product.setCategory(newProduct.getCategory());
                    product.setImageUrl(newProduct.getImageUrl());
                    product.setPrice(newProduct.getPrice());
                    product.setTitle(newProduct.getTitle());
                    return repo.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setKey(id);
                    return repo.save(newProduct);
                });

        EntityModel<Product> entityModel = assembler.toModel(updatedProduct);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(entityModel);

    }

    // Delete Product by id
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/products/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable String id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

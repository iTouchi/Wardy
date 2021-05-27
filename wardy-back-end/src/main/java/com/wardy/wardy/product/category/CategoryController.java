package com.wardy.wardy.product.category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CategoryController {

    private final CategoryRepository repo;
    private final CategoryModelAssembler assembler;

    public CategoryController(CategoryRepository repo, CategoryModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    // Get all Categories
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/categories")
    CollectionModel<EntityModel<Category>> all() {

        List<EntityModel<Category>> categories = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(categories,
                linkTo(methodOn(CategoryController.class).all()).withSelfRel());

    }

    // Get one Category by id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/categories/{id}")
    EntityModel<Category> one(@PathVariable String id) {

        Category category = repo.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException(id));

        return assembler.toModel(category);
    }
}

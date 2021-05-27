package com.wardy.wardy.product.category;

import com.wardy.wardy.product.ProductController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>> {

    @Override
    public EntityModel<Category> toModel(Category entity)  {

        return new EntityModel<>(entity,

                linkTo(methodOn(CategoryController.class).one(entity.getId())).withSelfRel(),

                linkTo(methodOn(CategoryController.class).all()).withRel("categories"));
    }
}

package com.wardy.wardy.shoppingcart;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShoppingCartModelAssembler implements
        RepresentationModelAssembler<ShoppingCart, EntityModel<ShoppingCart>> {

    @Override
    public EntityModel<ShoppingCart> toModel(ShoppingCart entity) {


        return new EntityModel<>(entity,
                linkTo(methodOn(ShoppingCartController.class).one(entity.getId())).withSelfRel(),

                linkTo(methodOn(ShoppingCartController.class).all()).withRel("carts"));
    }
}

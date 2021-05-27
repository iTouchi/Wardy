# Wardy
Project Description
## Front-end
Front-end description
## Back-end
Back-end description
## RESTapi endpoints

```c++
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

```


![alt text](images/star.png "Star Icon")







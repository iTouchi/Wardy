package com.wardy.wardy.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.wardy.wardy.UserService;
import com.wardy.wardy.auth.AuthenticationRequest;
import com.wardy.wardy.auth.AuthenticationResponse;
import com.wardy.wardy.auth.JwtUtil;
import com.wardy.wardy.auth.MyUserDetailsService;
import com.wardy.wardy.product.Product;
import com.wardy.wardy.product.ProductRepository;
import com.wardy.wardy.task.Task;
import com.wardy.wardy.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SysexMessage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired

    private final UserRepository userRepo;
    private final UserModelAssembler assembler;
    private final ProductRepository productRepo;
    private final TaskRepository taskRepo;


    public UserController(UserRepository userRepo,
                          UserModelAssembler assembler,
                          ProductRepository productRepo,
                          TaskRepository taskRepo) {
        this.userRepo = userRepo;
        this.assembler = assembler;
        this.productRepo = productRepo;
        this.taskRepo = taskRepo;
    }

    // Get all users
    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {

        List<EntityModel<User>> users = userRepo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    // Create new User
    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(userRepo.save(newUser));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Get one User by id
    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable String id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);

    }

    // Method that returns a specific user from reading the token out of the Authentication header
    @GetMapping(path = "/one", headers = {"Authentication"})
    public EntityModel<User> fetchSpecificUser() {
        String token = httpServletRequest.getHeader("Authentication");
        if (token.equals("Bearer null")) {
            return null;
        } else {
            String normalizedToken = token.substring(7);
            User user = userService.loadUserByUsername(jwtService.extractUsername(normalizedToken));

            return assembler.toModel(user);
        }
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Incorrect name and/or password", badCredentialsException);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    // Update User by id
    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable String id) {

        User updatedUser = userRepo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setIsAdmin(newUser.getIsAdmin());
                    user.setBirthDate(newUser.getBirthDate());
                    user.setProducts(newUser.getProducts());
                    user.setPassword(newUser.getPassword());
                    return userRepo.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepo.save(newUser);
                });

        EntityModel<User> entityModel = assembler.toModel(updatedUser);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(entityModel);

    }

    // Delete User by id
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable String id) {
        userRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    // Get Products form User
    @GetMapping("/users/{id}/products")
    List<Product> getAllProducts(@PathVariable String id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.get().getProducts();
    }


    // Add/Create Product to/for User
    @PostMapping("users/{id}/products")
    ResponseEntity<Object> addProduct(@PathVariable String id, @RequestBody Product product) {


        Optional<User> userOptional = userRepo.findById(id);

//        if (!userOptional.isPresent()) throw new UserNotFoundException("id-" + id);

        User user = userOptional.get();

        product.setUser(user);
        productRepo.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getKey())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // Create Task for User
    @PostMapping("users/{id}/tasks")
    ResponseEntity<Object> addTask(@PathVariable String id, @RequestBody Task task) {


        Optional<User> userOptional = userRepo.findById(id);

        User user = userOptional.get();

        task.setUser(user);
        taskRepo.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getKey())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // Get Tasks form User
    @GetMapping("/users/{id}/tasks")
    List<Task> getAllTasks(@PathVariable String id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.get().getTasks();
    }

}

// U
// ONE:     402881ee728b6a6001728b6a6ee40000
// TWO:     402881ee728b6a6001728b6a6ff90001
// P
// ONE:     402881ee728b6a6001728b6a700c0002
// TWO:     402881ee728b6a6001728b6a701b0003


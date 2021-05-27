package com.wardy.wardy.task;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TaskController {

    private final TaskRepository repo;
    private final TaskModelAssembler assembler;

    public TaskController(TaskRepository repo, TaskModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    //Get all Task
    @GetMapping("/tasks")
    CollectionModel<EntityModel<Task>> all() {

        List<EntityModel<Task>> tasks = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(tasks,
                linkTo(methodOn(TaskController.class).all()).withSelfRel());
    }

    // Get one Task by id
    @GetMapping("/tasks/{id}")
    EntityModel<Task> one(@PathVariable String id) {

        Task task = repo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return assembler.toModel(task);
    }


}

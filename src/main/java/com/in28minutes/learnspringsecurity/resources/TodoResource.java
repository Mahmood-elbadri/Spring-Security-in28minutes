package com.in28minutes.learnspringsecurity.resources;

import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://www.in28minutes.com")
@RestController
@EnableMethodSecurity
public class TodoResource {
    Logger logger = LoggerFactory.getLogger(getClass());
    public static final List<Todo> TODO_LIST = List.of(new Todo("dodo", "Learn Full Stack"),
            new Todo("didi", "Learn SpringBoot"));

    @GetMapping("/todos")
    public List<Todo> sayHi(){
        return TODO_LIST;
    }
    @GetMapping("/users/{username}/todos")
    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    //@PostAuthorize("returnObject.username == 'dodo'")
//    @RolesAllowed({"ADMIN","USER"})
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<Todo> retrieveUser(@PathVariable ("username") String name){
        return TODO_LIST.stream().toList();
    }
    @PostMapping("/users/{username}/todos")
    public void createTodo(@PathVariable String username, @RequestBody Todo todo){
        logger.info("Create {} for {}",todo,username);
    }
}
record Todo(String username,String description){}

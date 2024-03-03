package com.in28minutes.learnspringsecurity.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://www.in28minutes.com")
@RestController
public class TodoResource {
    Logger logger = LoggerFactory.getLogger(getClass());
    public static final List<Todo> TODO_LIST = List.of(new Todo("dodo", "Learn Full Stack"),
            new Todo("didi", "Learn SpringBoot"));

    @GetMapping("/todos")
    public List<Todo> sayHi(){
        return TODO_LIST;
    }
    @GetMapping("/users/{username}/todos")
    public Todo retrieveUser(@PathVariable ("username") String name){
        return TODO_LIST.get(0);
    }
    @PostMapping("/users/{username}/todos")
    public void createTodo(@PathVariable String username, @RequestBody Todo todo){
        logger.info("Create {} for {}",todo,username);
    }
}
record Todo(String username,String description){}

package ru.diasoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.service.GreetingServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    @Autowired
    private GreetingServiceImpl greetingService;

    @GetMapping
    public List<Greeting> findAll() {
        return greetingService.findAll();
    }

    @GetMapping("{id}")
    public Greeting findOne(@PathVariable long id) {
        return greetingService.findById(id);
    }

    @PostMapping
    public Greeting addOne(@RequestBody Greeting greeting) {
        return greetingService.add(greeting);
    }

    @PutMapping
    public Greeting update(@RequestBody Greeting greeting) {
        return greetingService.update(greeting);
    }

    @DeleteMapping({"{id}"})
    public void delete(@PathVariable long id) {
        greetingService.remove(id);
    }

}

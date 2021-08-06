package com.example.restserviceapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final List<Greeting> greetings = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public List<Greeting> findAll() {
        return greetings;
    }

    @Override
    public Integer create(Greeting resource) {
        resource.setId(counter.incrementAndGet());
        greetings.add(resource);
        return resource.getId();
    }

    @Override
    public void update(Integer id, Greeting resource) {
        updateFields(resource, findById(id));
    }

    private void updateFields(Greeting source, Greeting target) {
        target.setName(source.getName());
    }

    @Override
    public Greeting findById(Integer id) {
        Optional<Greeting> optTarget = greetings.stream().filter(greeting -> greeting.getId().equals(id)).findFirst();
        if (optTarget.isPresent()) {
            return optTarget.get();
        } else
            throw new ResourceNotFoundException();
    }

    @Override
    public void deleteById(Integer id) {
        greetings.remove(findById(id));
    }
}

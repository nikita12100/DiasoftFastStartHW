package com.example.restserviceapp;

import java.util.List;

public interface GreetingService {

    List<Greeting> findAll();

    void update(Integer id, Greeting resource);

    Integer create(Greeting resource);

    Greeting findById(Integer id);

    void deleteById(Integer id);
}

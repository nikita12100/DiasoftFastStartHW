package ru.diasoft.demo.service;

import ru.diasoft.demo.domain.Greeting;

import java.util.List;

public interface GreetingService {

    List<Greeting> findAll();

    Greeting findById(long id);

    Greeting add(Greeting greeting);

    Greeting update(long id, Greeting greeting);

    void remove(long id);
}

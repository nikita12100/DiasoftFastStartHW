package ru.diasoft.demo.service;

import ru.diasoft.demo.domain.Greeting;

import java.util.List;


public interface GreetingService {

    public List<Greeting> findAll();

    public Greeting findById(long id);

    public Greeting add(Greeting greeting);

    public Greeting update(long id, Greeting greeting);

    public void remove(long id);
}

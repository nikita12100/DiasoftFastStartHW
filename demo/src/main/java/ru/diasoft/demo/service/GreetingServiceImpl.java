package ru.diasoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.exception.GreetingAlreadyExistsException;
import ru.diasoft.demo.exception.GreetingNotFoundException;
import ru.diasoft.demo.repository.GreetingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private GreetingRepository greetingRepo;

    public List<Greeting> findAll() {

        return greetingRepo.findAll();
    };

    public Greeting findById(long id) {

        Optional<Greeting> greeting = greetingRepo.findById(id);

        if (greeting.isPresent()) {
            return greeting.get();
        } else {
            throw new GreetingNotFoundException();
        }
    };

    public Greeting add(Greeting greeting) {

        Optional<Greeting> exists = greetingRepo.findById(greeting.getId());

        if (exists.isPresent()) {
            throw new GreetingAlreadyExistsException();
        } else {
            return greetingRepo.save(greeting);
        }
    };

    public Greeting update(Greeting greeting) {

        Optional<Greeting> exists = greetingRepo.findById(greeting.getId());

        if (exists.isPresent()) {
            return greetingRepo.save(greeting);
        } else {
            throw new GreetingNotFoundException();
        }
    };

    public void remove(long id) {
        greetingRepo.deleteById(id);
    };
}

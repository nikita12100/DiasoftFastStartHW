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
    private GreetingRepository greetingRepository;

    public List<Greeting> findAll() {
        return greetingRepository.findAll();
    };

    public Greeting findById(long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);

        if (greeting.isPresent()) {
            return greeting.get();
        } else {
            throw new GreetingNotFoundException();
        }
    };

    public Greeting add(Greeting greeting) {
        Optional<Greeting> exists = greetingRepository.findById(greeting.getId());

        if (exists.isPresent()) {
            throw new GreetingAlreadyExistsException();
        } else {
            return greetingRepository.save(greeting);
        }
    };

    public Greeting update(long id, Greeting greeting) {
        Optional<Greeting> exists = greetingRepository.findById(id);

        if (exists.isPresent()) {
            greeting.setId(id);
            return greetingRepository.save(greeting);
        } else {
            throw new GreetingNotFoundException();
        }
    };

    public void remove(long id) {
        greetingRepository.deleteById(id);
    };
}

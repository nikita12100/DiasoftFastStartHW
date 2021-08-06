package ru.diasoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.exception.GreetingAlreadyExistsException;
import ru.diasoft.demo.exception.GreetingNotFoundException;
import ru.diasoft.demo.repository.GreetingRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private GreetingRepository greetingRepo;

    @Transactional(readOnly = true)
    public List<Greeting> findAll() {

        return greetingRepo.findAll();
    };

    @Transactional(readOnly = true)
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

    public Greeting update(long id, Greeting greeting) {

        Optional<Greeting> exists = greetingRepo.findById(id);

        if (exists.isPresent()) {
            greeting.setId(id);
            return greetingRepo.save(greeting);
        } else {
            throw new GreetingNotFoundException();
        }
    };

    public void remove(long id) {
        greetingRepo.deleteById(id);
    };
}

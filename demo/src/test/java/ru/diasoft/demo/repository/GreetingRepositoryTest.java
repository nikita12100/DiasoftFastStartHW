package ru.diasoft.demo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.demo.domain.Greeting;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GreetingRepositoryTest {

    @Autowired
    private GreetingRepository greetingRepo;

    @Test
    public void findAllTest() {

        final String name = "anybody";

        Greeting testGreet = new Greeting(name);
        int sizeShouldIncrement = greetingRepo.findAll().size() + 1;
        greetingRepo.save(testGreet);

        Assert.assertEquals(sizeShouldIncrement, greetingRepo.findAll().size());
    }
}
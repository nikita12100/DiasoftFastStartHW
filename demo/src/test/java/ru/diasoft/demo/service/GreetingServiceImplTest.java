package ru.diasoft.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.repository.GreetingRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        GreetingServiceImpl.class
})
public class GreetingServiceImplTest {

    @Autowired
    private GreetingService greetingService;

    @MockBean
    private GreetingRepository greetingRepo;


    @Test
    public void getTest() {
        final String name = "anyName";
        Greeting test = new Greeting(name);

        when(greetingRepo.findById(anyLong()))
                .thenReturn(java.util.Optional.of(test));
        Greeting found = greetingService.findById(1L);

        assertEquals(test.getName(), found.getName());
    }
}

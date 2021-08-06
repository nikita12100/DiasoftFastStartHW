package ru.diasoft.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.demo.DemoApplication;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.repository.GreetingRepository;
import ru.diasoft.demo.service.GreetingServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties"
)
@Transactional
public class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GreetingRepository greetingRepo;

    @MockBean
    private GreetingServiceImpl greetingService;


    @Test
    public void getNewGreetingTest() throws Exception {

        final String name = "Nikita";
        final String url = "/greeting/3";
        Greeting test = new Greeting(name);

        greetingRepo.save(test);
        when(greetingService.findById(anyLong())).thenReturn(test);

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(name)));
    }

    @Test
    public void findAllTest() throws Exception {

        final String name = "Nikita";
        final String url = "/greeting";
        Greeting test = new Greeting(name);

        List<Greeting> greetingList = Collections.singletonList(test);
        when(greetingService.findAll()).thenReturn(greetingList);

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(name)));
    }
}

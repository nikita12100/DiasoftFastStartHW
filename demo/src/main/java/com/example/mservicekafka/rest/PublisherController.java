package com.example.mservicekafka.rest;

import com.example.mservicekafka.domain.Message;
import com.example.mservicekafka.kafka.GreetingsGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class PublisherController {

    private final GreetingsGateway gateway;

    public PublisherController(GreetingsGateway gateway) {
        this.gateway = gateway;
    }

    @GetMapping(value = "/hello/{name}")
    public ResponseEntity<String> sendMessageToKafka(@PathVariable int name) {
        String message = "Hello, " + name;

        gateway.directGreet(Message.builder().id(new Date().getTime()).message(message).build());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

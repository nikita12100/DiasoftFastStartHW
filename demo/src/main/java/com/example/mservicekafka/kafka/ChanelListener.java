package com.example.mservicekafka.kafka;

import com.example.mservicekafka.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChanelListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // В микросервисе в обработчике вывести в лог полученное сообщение.
    @StreamListener(Topics.DEMO)
    public void demo(Message message) {
        logger.info("Message: " + message);
    }

}
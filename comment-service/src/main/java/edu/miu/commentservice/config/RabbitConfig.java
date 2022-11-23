package edu.miu.commentservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue hiQueue1(){
        return new Queue("queue-1", true);
    }

    @Bean
    FanoutExchange helloFanoutExchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding helloQueue1Binding(Queue hiQueue1, FanoutExchange helloFanoutExchange){
        return BindingBuilder.bind(hiQueue1).to(helloFanoutExchange);
    }
}

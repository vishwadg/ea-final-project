package edu.miu.movieservice.services.impl;

import edu.miu.movieservice.services.CommentRabbitService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CommentRabbitServiceImpl implements CommentRabbitService {
    @Override
    @RabbitListener(queues = {"queue-1"})
    public void getCommentBack(String payload) {
        System.out.println("This is from Media Service: "+payload);
    }
}

package edu.miu.commentservice.consumers;

import edu.miu.commentservice.entity.dto.KafkaMediaDto;
import edu.miu.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaKafkaConsumer {

    @Autowired
    CommentService commentService;

    @KafkaListener(topics = {"${spring.kafka.custom.media-topic}"}, containerFactory = "kafkaListenerContainerFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeMediaId(KafkaMediaDto kafkaMediaDto) {
        System.out.println("Inside Comment consumeMediaId ==>> " + kafkaMediaDto);
        //when media is deleted then delete comment too
        commentService.deleteByMediaId(kafkaMediaDto.getId());
    }
}

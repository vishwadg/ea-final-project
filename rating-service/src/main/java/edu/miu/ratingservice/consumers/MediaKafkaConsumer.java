package edu.miu.ratingservice.consumers;

import edu.miu.ratingservice.entity.dto.KafkaMediaDto;
import edu.miu.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaKafkaConsumer {

    @Autowired
    RatingService ratingService;

    @KafkaListener(topics = {"${spring.kafka.custom.media-topic}"}, containerFactory = "kafkaListenerContainerFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeMediaId(KafkaMediaDto kafkaMediaDto) {
        System.out.println("consumeMediaId ==>> " + kafkaMediaDto);
        //when media is deleted then delete rating too
        ratingService.deleteRatingByMedia(kafkaMediaDto.getId());
    }
}

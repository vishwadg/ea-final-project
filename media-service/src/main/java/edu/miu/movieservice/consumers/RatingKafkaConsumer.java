package edu.miu.movieservice.consumers;

import edu.miu.movieservice.entities.DTOs.AvgRatingDto;
import edu.miu.movieservice.entities.DTOs.MediaDTO;
import edu.miu.movieservice.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingKafkaConsumer {

    @Autowired
    MediaService mediaService;

    @KafkaListener(topics = {"${spring.kafka.custom.avg-rating-topic}"}, containerFactory = "kafkaListenerContainerFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeAvgRating(AvgRatingDto avgRatingDTO) {
        System.out.println("avgRatingDTO ==>> " + avgRatingDTO);
        //when there is change in average rating update it in media
        MediaDTO mediaDTO = mediaService.getById(avgRatingDTO.mediaId);
        mediaDTO.setAvgRating(avgRatingDTO.getAverageRating());
        mediaService.update(avgRatingDTO.getMediaId(), mediaDTO);
    }
}

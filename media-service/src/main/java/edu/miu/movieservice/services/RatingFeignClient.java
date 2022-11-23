package edu.miu.movieservice.services;

import edu.miu.movieservice.entities.DTOs.AvgRatingDTO;
import edu.miu.movieservice.entities.DTOs.RatingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("rating-service")
public interface RatingFeignClient {
    @GetMapping("/ratings/filter-by-media/{id}")
    List<RatingDTO> getRatingsByMediaId(@PathVariable Long id);

    @GetMapping("/ratings/average-rating/{mediaId}/media")
    ResponseEntity<AvgRatingDTO> getAverageRatingOfMedia(@PathVariable Long mediaId);
}

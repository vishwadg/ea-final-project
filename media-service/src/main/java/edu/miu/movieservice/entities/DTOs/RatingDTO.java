package edu.miu.movieservice.entities.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingDTO {
    private Long id;
    private int rating;
    private LocalDateTime localDateTime;
}

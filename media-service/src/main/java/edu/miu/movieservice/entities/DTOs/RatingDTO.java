package edu.miu.movieservice.entities.DTOs;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RatingDTO {
    private Long id;
    private double rating;
    private Date createdAt;
    private String userId;
    private long mediaId;
}

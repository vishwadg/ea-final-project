package edu.miu.movieservice.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgRatingDTO {
    public Long mediaId;
    public Double averageRating;
}

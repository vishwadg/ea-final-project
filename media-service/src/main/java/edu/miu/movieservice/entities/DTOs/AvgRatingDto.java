package edu.miu.movieservice.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgRatingDto {
    public Long mediaId;
    public Double averageRating;
}

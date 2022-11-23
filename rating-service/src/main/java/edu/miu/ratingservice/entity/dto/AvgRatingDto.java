package edu.miu.ratingservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
public class AvgRatingDto {
    public Long mediaId;
    public Double averageRating;
}

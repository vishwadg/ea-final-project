package edu.miu.ratingservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgRatingDto {
    public Long mediaId;
    public Double averageRating;
}

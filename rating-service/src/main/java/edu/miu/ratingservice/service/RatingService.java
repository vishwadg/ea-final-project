package edu.miu.ratingservice.service;


import edu.miu.ratingservice.entity.Rating;
import edu.miu.ratingservice.entity.dto.AvgRatingDto;
import edu.miu.ratingservice.entity.dto.RatingDto;

import java.util.List;

public interface RatingService {
    public List<RatingDto> getAllRatings();
    public List<RatingDto> getAllRatingsByUser(Long userId);
    public List<RatingDto> getAllRatingsByMedia(Long mediaId);
    public RatingDto getRatingById(Long ratingId);
    public AvgRatingDto getAverageRatingOfMedia(Long mediaId);

    public boolean upsertRating(RatingDto rating);

    public boolean deleteRating(Long ratingId);
    public boolean deleteRatingByUser(Long userId);
    public boolean deleteRatingByMedia(Long mediaId);


}

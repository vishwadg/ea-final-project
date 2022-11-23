package edu.miu.ratingservice.service;


import edu.miu.ratingservice.entity.Rating;

import java.util.List;

public interface RatingService {
    public List<Rating> getAllRatings();
    public List<Rating> getAllRatingsByUser(Long userId);
    public List<Rating> getAllRatingsByMedia(Long mediaId);
    public Rating getRatingById(Long ratingId);
    public Double getAverageRatingOfMedia(Long mediaId);

    public boolean upsertRating(Rating rating);

    public boolean deleteRating(Long ratingId);
    public boolean deleteRatingByUser(Long userId);
    public boolean deleteRatingByMedia(Long mediaId);


}

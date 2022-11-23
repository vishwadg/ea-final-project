package edu.miu.ratingservice.service.impl;

import edu.miu.ratingservice.entity.Rating;
import edu.miu.ratingservice.repository.RatingRepo;
import edu.miu.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    public final RatingRepo ratingRepo;

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByUser(Long userId) {
        return  ratingRepo.getRatingByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingsByMedia(Long mediaId) {
        return ratingRepo.getRatingsByMediaId(mediaId);
    }

    @Override
    public Rating getRatingById(Long ratingId) {
        return ratingRepo.findById(ratingId).get();
    }

    @Override
    public Double getAverageRatingOfMedia(Long mediaId) {
        return ratingRepo.getAverageRatingOfMedia(mediaId);
    }

    @Override
    public boolean upsertRating(Rating rating) {
        try
        {
            ratingRepo.save(rating);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }

    }

    @Override
    public boolean deleteRating(Long ratingId) {
        try{
            ratingRepo.deleteById(ratingId);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public boolean deleteRatingByUser(Long userId) {
        try{
           var item = ratingRepo.deleteAllByUserId(userId);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public boolean deleteRatingByMedia(Long mediaId) {
        try{
            ratingRepo.deleteAllByMediaId(mediaId);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }
}

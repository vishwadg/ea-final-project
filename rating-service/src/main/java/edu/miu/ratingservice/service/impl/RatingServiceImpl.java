package edu.miu.ratingservice.service.impl;

import edu.miu.ratingservice.entity.Rating;
import edu.miu.ratingservice.entity.dto.AvgRatingDto;
import edu.miu.ratingservice.entity.dto.RatingDto;
import edu.miu.ratingservice.repository.RatingRepo;
import edu.miu.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {



    public final RatingRepo ratingRepo;
    public final ModelMapper modelMapper;

    @Override
    public List<RatingDto> getAllRatings() {
        var item = ratingRepo.findAll();
       return item.stream().map(rating -> modelMapper.map(rating, RatingDto.class)).toList();
    }

    @Override
    public List<RatingDto> getAllRatingsByUser(Long userId) {
        var item =  ratingRepo.getRatingByUserId(userId);
        return item.stream().map(rating -> modelMapper.map(rating,RatingDto.class)).toList();
    }

    @Override
    public List<RatingDto> getAllRatingsByMedia(Long mediaId) {
        var item = ratingRepo.getRatingsByMediaId(mediaId);
        return item.stream().map(rating -> modelMapper.map(rating,RatingDto.class)).toList();
    }

    @Override
    public RatingDto getRatingById(Long ratingId) {
        var rating =  ratingRepo.findById(ratingId).get();
        return modelMapper.map(rating,RatingDto.class);
    }

    @Override
    public AvgRatingDto getAverageRatingOfMedia(Long mediaId) {
         var averageRating = ratingRepo.getAverageRatingOfMedia(mediaId);
         return new AvgRatingDto(mediaId,averageRating);
    }

    @Override
    public boolean upsertRating(RatingDto rating) {
        try
        {
            ratingRepo.save(modelMapper.map(rating,Rating.class));
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

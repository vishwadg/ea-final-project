package edu.miu.ratingservice.repository;

import edu.miu.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating,Long> {
    public List<Rating> getRatingByUserId(Long userId);
    public List<Rating> getRatingsByMediaId(Long mediaId);

    @Query(value = "select AVG(rating) from rating_table r where r.media_id = :mediaId",nativeQuery = true)
    public Double getAverageRatingOfMedia(
            @Param("mediaId") Long mediaId);

    @Transactional
    @Modifying
    public Integer deleteAllByUserId(long userId);
    @Transactional
    @Modifying
    public Integer deleteAllByMediaId(Long mediaId);
}

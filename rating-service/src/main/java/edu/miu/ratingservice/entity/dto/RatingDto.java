package edu.miu.ratingservice.entity.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
public class RatingDto {
    private long id;
    private double rating;
    private Date createdAt;
    private long mediaId;
    private String userId;
}

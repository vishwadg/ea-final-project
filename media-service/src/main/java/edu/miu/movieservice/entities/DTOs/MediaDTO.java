package edu.miu.movieservice.entities.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MediaDTO {
    private Long id;
    private String name;
    private int releasedYear;
    private String mediaType;
    private String genre;
    private String director;
    private String actor;
    private double duration;
    private Date postedDate;
    private double avgRating;
    private Long grossIncome;
    private int seasons;
    private List<CommentDTO> commentList;
    private List<RatingDTO> ratingList;
}

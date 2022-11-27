package edu.miu.movieservice.entities.DTOs;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommentDTO {
    private Long id;
    private String comment;
    private Long mediaId;
    private Date createdAt;
    private String userId;
}

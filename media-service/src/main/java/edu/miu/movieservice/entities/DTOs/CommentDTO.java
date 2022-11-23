package edu.miu.movieservice.entities.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String comment;
    private LocalDateTime localDateTime;
}

package edu.miu.commentservice.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String comment;
    private Long mediaId;
    private LocalDateTime createdAt;
}

package edu.miu.commentservice.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    private String comment;
    private Long mediaId;
    private String userId;
    private Date createdAt;
}

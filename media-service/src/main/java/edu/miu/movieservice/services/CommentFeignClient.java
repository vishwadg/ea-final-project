package edu.miu.movieservice.services;

import edu.miu.movieservice.entities.DTOs.CommentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("comment-service")
public interface CommentFeignClient {
    @GetMapping("/comments/media/{id}")
    List<CommentDTO> getCommentsByMediaId(@PathVariable Long id);
}

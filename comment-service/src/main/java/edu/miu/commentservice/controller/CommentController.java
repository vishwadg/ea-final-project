package edu.miu.commentservice.controller;

import edu.miu.commentservice.entity.dto.CommentDto;
import edu.miu.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;



   @GetMapping("/test")
   public String getText(){
       return commentService.justCall();
   }

   @GetMapping
   public List<CommentDto> getAll(){
       return commentService.getAll();
   }

   @GetMapping("/media/{id}")
   public List<CommentDto> getByMediaId(@PathVariable Long id){
       return commentService.getByMediaId(id);
   }

   @GetMapping("/{id}")
   public CommentDto getById(@PathVariable Long id){
       return commentService.getById(id);
   }

   @PostMapping()
   public CommentDto save(@RequestBody CommentDto body){
       return commentService.save(body);
   }

   @PutMapping("/{id}")
   public CommentDto update(@PathVariable Long id,   @RequestBody CommentDto body){
       return commentService.update(id, body);
   }


   @DeleteMapping("/{id}")
   public CommentDto delete(@PathVariable Long id){
       return commentService.delete(id);
   }
}

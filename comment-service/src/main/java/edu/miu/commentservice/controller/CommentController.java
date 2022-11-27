package edu.miu.commentservice.controller;

import edu.miu.commentservice.entity.dto.CommentDto;
import edu.miu.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import java.util.List;
import java.security.Principal;

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
   @RolesAllowed({"member"})
   public CommentDto save(@RequestBody CommentDto body, Principal principal){
       body.setUserId(principal.getName());
       return commentService.save(body);
   }

   @PutMapping("/{id}")
   @RolesAllowed({"member"})
   public CommentDto update(@PathVariable Long id,   @RequestBody CommentDto body){
       return commentService.update(id, body);
   }


   @DeleteMapping("/{id}")
   @RolesAllowed({"member"})
   public CommentDto delete(@PathVariable Long id){
       return commentService.delete(id);
   }
}

package edu.miu.commentservice.service;

import edu.miu.commentservice.entity.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    //test
   String justCall();

   //get
   List<CommentDto> getAll();

   //get by id
   CommentDto getById(Long id);
   List<CommentDto> getByMediaId(Long id);

   // post
   CommentDto save(CommentDto body);

   //update
   CommentDto update(Long id, CommentDto body);

   //delete
   CommentDto delete(Long id);

}

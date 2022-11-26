package edu.miu.commentservice.service.impl;

import edu.miu.commentservice.entity.Comment;
import edu.miu.commentservice.entity.dto.CommentDto;
import edu.miu.commentservice.repo.CommentRepo;
import edu.miu.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ModelMapper modelMapper;

    private final RabbitTemplate rabbitTemplate;
    private final Queue hiQueue1;

    @Override
    public String justCall() {
        return "Called";
    }

    @Override
    public List<CommentDto> getAll() {
        var comments = commentRepo.findAll();
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getById(Long id) {
        var comment = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getByMediaId(Long id) {
        var comments = commentRepo.findCommentByMediaId(id);
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto save(CommentDto body) {
        Comment save = commentRepo.save(modelMapper.map(body, Comment.class));
        rabbitTemplate.convertAndSend(this.hiQueue1.getName(), "Came from: Comment Service" + System.currentTimeMillis());
        return modelMapper.map(save, CommentDto.class);
    }

    @Override
    public CommentDto update(Long id, CommentDto body) {
        var comment = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        if (body.getId() != id) throw new RuntimeException("Id not matched");
        return modelMapper.map(commentRepo.save(modelMapper.map(body, Comment.class)), CommentDto.class);
    }

    @Override
    public CommentDto delete(Long id) {
        var comment = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        commentRepo.delete(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public String deleteByMediaId(Long mediaId) {
        commentRepo.deleteAllByMediaId(mediaId).orElseThrow(() -> new RuntimeException("Media id not found"));
        return "Successfully deleted all comments of media id : " + mediaId;
    }

}

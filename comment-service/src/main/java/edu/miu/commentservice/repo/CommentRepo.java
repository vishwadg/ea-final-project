package edu.miu.commentservice.repo;

import edu.miu.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    public List<Comment> findCommentByMediaId(Long id);

}

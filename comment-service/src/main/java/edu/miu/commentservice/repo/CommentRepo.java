package edu.miu.commentservice.repo;

import edu.miu.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    public List<Comment> findCommentByMediaId(Long id);

    @Transactional
    @Modifying
    Optional<String> deleteAllByMediaId(Long mediaId);

}

package miu.edu.repository;

import miu.edu.domain.Avatar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepo extends MongoRepository<Avatar,String> {
    public Avatar getAvatarByStudentId(String studentId);
}

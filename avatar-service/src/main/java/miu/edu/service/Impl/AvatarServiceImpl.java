package miu.edu.service.Impl;

import lombok.RequiredArgsConstructor;
import miu.edu.domain.Avatar;
import miu.edu.repository.AvatarRepo;
import miu.edu.service.AvatarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final AvatarRepo avatarRepo;
    @Override
    public Avatar getAvatar(String id) {
       return avatarRepo.findById(id).get();
    }

    @Override
    public Avatar saveAvatar(Avatar avatar) {
        return avatarRepo.save(avatar);
    }

    @Override
    public Avatar updateAvatar(Avatar avatar) {
        return avatarRepo.save(avatar);
    }

    @Override
    public void deleteAvatar(String id) {
        avatarRepo.deleteById(id);
    }

    @Override
    public Avatar getAvatarByStudentId(String studentId) {
        return  avatarRepo.getAvatarByStudentId(studentId);
    }

    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepo.findAll();
    }
}

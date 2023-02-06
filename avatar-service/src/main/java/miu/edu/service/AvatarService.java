package miu.edu.service;

import miu.edu.domain.Avatar;

import java.util.List;

public interface AvatarService {
    public Avatar getAvatar(String id);
    public Avatar saveAvatar(Avatar avatar);
    public Avatar updateAvatar(Avatar avatar);
    public void deleteAvatar(String id);
    public Avatar getAvatarByStudentId(String studentId);
    public List<Avatar> getAllAvatars();

}

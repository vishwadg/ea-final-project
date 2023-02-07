package miu.edu.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.domain.Avatar;
import miu.edu.feign.TestFeign;
import miu.edu.service.AvatarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {
    private final AvatarService avatarService;

    private final TestFeign testFeign;


    @GetMapping
    public List<Avatar> getAllAvatars() {
        return avatarService.getAllAvatars();
    }

    @GetMapping("/{id}")
    public Avatar getAvatar(@PathVariable String id) {
        return avatarService.getAvatar(id);
    }

    @GetMapping("/student/{studentId}")
    public Avatar getAvatarByStudentId(@PathVariable String studentId) {
        return avatarService.getAvatarByStudentId(studentId);
    }

    @PostMapping
    public Avatar saveAvatar(@RequestBody Avatar avatar) {
        return avatarService.saveAvatar(avatar);
    }

    @PutMapping
    public Avatar updateAvatar(@RequestBody Avatar avatar) {
        return avatarService.updateAvatar(avatar);
    }

    @DeleteMapping("/{id}")
    public void deleteAvatar(@PathVariable String id) {
        avatarService.deleteAvatar(id);
    }


    @GetMapping("/call-user")
    public String CallUser() {
        return testFeign.hello();
    }

}

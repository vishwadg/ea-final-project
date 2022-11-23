package edu.miu.ratingservice.controller;

import edu.miu.ratingservice.entity.Rating;
import edu.miu.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")

public class RatingController {
    public final RatingService ratingService;

    public static final String ratingNotFound = "No Ratings Found";
    public static final String cannotBeZero = "cannot be zero";

    @GetMapping()
    public ResponseEntity<List<Rating>> GetAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/filter-by-user")
    public ResponseEntity getAllRatingsByUser(@RequestParam Long userId) {
        if (userId == 0) return sendBadRequest("UserId" + cannotBeZero);
        var item = ratingService.getAllRatingsByUser(userId);
        if (item == null) return sendBadRequest(ratingNotFound);
        else return ResponseEntity.ok(item);
    }

    @GetMapping("/filter-by-media")
    public ResponseEntity<List<Rating>> getAllRatingsByMedia(@RequestParam Long mediaId) {
        if (mediaId == 0) return sendBadRequest("Media Id" + cannotBeZero);
        var item = ratingService.getAllRatingsByMedia(mediaId);
        if (item == null) return sendBadRequest(ratingNotFound);
        else return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        if (id == 0) return sendBadRequest("Rating Id" + cannotBeZero);
        var item = ratingService.getRatingById(id);
        if (item == null) return sendBadRequest(ratingNotFound);
        else return ResponseEntity.ok(item);
    }

    @GetMapping("/average-rating/{mediaId}/media")
    public ResponseEntity<Double> getAverageRatingOfMedia(@PathVariable Long mediaId) {
        if (mediaId == 0) return sendBadRequest("Media Id " + cannotBeZero);
        var item = ratingService.getAverageRatingOfMedia(mediaId);
        if (item == null) return sendBadRequest(ratingNotFound);
        else return ResponseEntity.ok(ratingService.getAverageRatingOfMedia(mediaId));
    }

    @PostMapping()
    public ResponseEntity Save(@RequestBody Rating rating) {
        if (rating == null || rating.getId() != 0) return sendBadRequest("Cannot save");
        var isSaved = ratingService.upsertRating(rating);
        if (isSaved)
            return ResponseEntity.ok("SuccessFully Saved");
        else return sendBadRequest("Cannot Save");
    }

    @PutMapping()
    public ResponseEntity Update(@RequestBody Rating rating) {
        if (rating == null || rating.getId() == 0) return sendBadRequest("Cannot Update");
        var isUpdated = ratingService.upsertRating(rating);
        if (isUpdated)
            return ResponseEntity.ok("SuccessFully Updated");
        else return sendBadRequest("cannot Update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (id == 0) sendBadRequest("Rating Id" + cannotBeZero);
        var isDeleted = ratingService.deleteRating(id);
        if (isDeleted)
            return ResponseEntity.ok("SuccessFully Deleted");
        else return sendBadRequest("Cannot delete");
    }

    @DeleteMapping("/{userId}/user")
    public ResponseEntity deleteRatingByUser(@PathVariable Long userId) {
        if (userId == 0) sendBadRequest("User Id" + cannotBeZero);
        var isDeleted = ratingService.deleteRatingByUser(userId);
        if (isDeleted)
            return ResponseEntity.ok("SuccessFully Deleted");
        else return sendBadRequest("Cannot delete");
    }

    @DeleteMapping("/{mediaId}/media")
    public ResponseEntity deleteRatingByMedia(@PathVariable Long mediaId) {
        if (mediaId == 0) sendBadRequest("User Id" + cannotBeZero);
        var isDeleted = ratingService.deleteRatingByMedia(mediaId);
        if (isDeleted)
            return ResponseEntity.ok("SuccessFully Deleted");
        else return sendBadRequest("Cannot delete");
    }

    private ResponseEntity sendBadRequest(String msg) {
        return ResponseEntity.badRequest().body(msg);
    }
}

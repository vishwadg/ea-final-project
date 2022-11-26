package edu.miu.movieservice.controllers;

import edu.miu.movieservice.entities.DTOs.MediaDTO;
import edu.miu.movieservice.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/media")
public class MovieController {

    @Autowired
    MediaService mediaService;

    @PostMapping
    @RolesAllowed({"manager"})
    ResponseEntity<?> create(@RequestBody MediaDTO mediaDTO) {
        return new ResponseEntity<>(mediaService.create(mediaDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"manager"})
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody MediaDTO mediaDTO) {
        return new ResponseEntity<>(mediaService.update(id, mediaDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mediaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> search(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams) {
        return new ResponseEntity<>(mediaService.filter(pageable, queryParams), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<?> getAllMedia() {
        return new ResponseEntity<>(mediaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/by-type")
    ResponseEntity<?> getByMediaType(@RequestParam(name = "mediaType") String mediaType) {
        return new ResponseEntity<>(mediaService.getAllMediaByType(mediaType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"manager"})
    ResponseEntity<?> remove(@PathVariable Long id) {
        return new ResponseEntity<>(mediaService.delete(id), HttpStatus.OK);
    }
}

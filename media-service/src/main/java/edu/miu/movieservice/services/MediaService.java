package edu.miu.movieservice.services;

import edu.miu.movieservice.entities.DTOs.MediaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public interface MediaService {

    MediaDTO create(MediaDTO mediaDTO);

    List<MediaDTO> filter(Pageable pageable, MultiValueMap<String, String> multiValueMap);

    List<MediaDTO> getAll();

    List<MediaDTO> getAllMediaByType(String mediaType);

    MediaDTO getById(Long id);

    MediaDTO update(Long id, MediaDTO mediaDTO);

    MediaDTO delete(Long id);
}

package edu.miu.movieservice.services.impl;

import edu.miu.movieservice.entities.DTOs.*;
import edu.miu.movieservice.entities.Media;
import edu.miu.movieservice.entities.Movie;
import edu.miu.movieservice.entities.TvSeries;
import edu.miu.movieservice.repositories.MediaRepository;
import edu.miu.movieservice.services.CommentFeignClient;
import edu.miu.movieservice.services.MediaService;
import edu.miu.movieservice.services.RatingFeignClient;
import edu.miu.movieservice.services.specification.MediaSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${spring.kafka.custom.media-topic}")
    private String movieTopic;
    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CommentFeignClient commentFeignClient;

    @Autowired
    RatingFeignClient ratingFeignClient;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    KafkaTemplate<String, KafkaMediaDto> kafkaTemplate;

    @Override
    public MediaDTO create(MediaDTO mediaDTO) {
        if (mediaDTO.getMediaType().equals("movie")) {
            Movie movie = new Movie();
            movie.setGrossIncome(movie.getGrossIncome());

            Movie media = modelMapper.map(mediaDTO, Movie.class);
            media.setMediaType(mediaDTO.getMediaType());

            Media mediaRepo = mediaRepository.save(media);
            return modelMapper.map(mediaRepo, MediaDTO.class);
        } else {
            TvSeries tvSeries = new TvSeries();
            tvSeries.setSeasons(mediaDTO.getSeasons());

            TvSeries media = modelMapper.map(mediaDTO, TvSeries.class);
            media.setMediaType(mediaDTO.getMediaType());

            Media mediaRepo = mediaRepository.save(media);
            return modelMapper.map(mediaRepo, MediaDTO.class);
        }
    }


    @Override
    public List<MediaDTO> getAll() {
        List<Media> mediaList = mediaRepository.findAll();
        if (mediaList.isEmpty()) {
            throw new RuntimeException("Sorry, media not found");
        }
        List<MediaDTO> mediaDTOList = mediaList.stream().map(
                media -> {
                    MediaDTO mediaDTO = modelMapper.map(media, MediaDTO.class);
                    return mediaDTO;
                }
        ).toList();
        return mediaDTOList;
    }

    @Override
    public List<MediaDTO> filter(Pageable pageable, MultiValueMap<String, String> queryParams) {

        Specification specification = Specification.where(null);
        if (queryParams.containsKey("releasedYear")) {
            specification = specification.and(MediaSpecification.mediaHasReleasedYear(Integer.parseInt(queryParams.getFirst("releasedYear"))));
        }
        if (queryParams.containsKey("rating")) {
            specification = specification.and(MediaSpecification.mediaHasRating(Double.parseDouble(queryParams.getFirst("rating"))));
        }
        if (queryParams.containsKey("genre")) {
            specification = specification.and(MediaSpecification.mediaHasGenre(queryParams.getFirst("genre")));
        }
        if (queryParams.containsKey("director")) {
            specification = specification.and(MediaSpecification.mediaHasDirector(queryParams.getFirst("director")));
        }
        if (queryParams.containsKey("actor")) {
            specification = specification.and(MediaSpecification.mediaHasActor(queryParams.getFirst("actor")));
        }
        if (queryParams.containsKey("duration")) {
            specification = specification.and(MediaSpecification.mediaHasDuration(Double.parseDouble(queryParams.getFirst("duration"))));
        }
        Page<Media> media = mediaRepository.findAll(specification, pageable);

        List<MediaDTO> mediaDTOList = media.stream().map(
                me -> {
                    MediaDTO mediaDTO = modelMapper.map(me, MediaDTO.class);
                    return mediaDTO;
                }
        ).toList();
        return mediaDTOList;
    }

    @Override
    public List<MediaDTO> getAllMediaByType(String mediaType) {
        List<Media> mediaList = mediaRepository.findAllByMediaType(mediaType);
        List<MediaDTO> mediaDTOList = mediaList.stream().map(media -> {
            MediaDTO mediaDTO = modelMapper.map(media, MediaDTO.class);
            return mediaDTO;
        }).toList();
        return mediaDTOList;
    }

    @Override
    public MediaDTO getById(Long id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        Optional<Media> mediaOptional = mediaRepository.findById(id);
        Media media = mediaOptional.orElseThrow(()-> new RuntimeException("No media found"));
        if (media == null) {
            throw new RuntimeException("Sorry, media not found");
        }
        MediaDTO mediaDTO = modelMapper.map(media, MediaDTO.class);

        List<CommentDTO> commentDTOList = circuitBreaker.run(() -> commentFeignClient.getCommentsByMediaId(id), throwable -> getDefaultCommentList());
        List<RatingDTO> ratingDTOList = circuitBreaker.run(() -> ratingFeignClient.getRatingsByMediaId(id), throwable -> getDefaultRatingList());
        ResponseEntity<AvgRatingDto> avgRating = circuitBreaker.run(() -> ratingFeignClient.getAverageRatingOfMedia(id), throwable -> getDefaultAverageRating(id));
        if (!commentDTOList.isEmpty()) {
            mediaDTO.setCommentList(commentDTOList);
        }

        if (!ratingDTOList.isEmpty()) {
            mediaDTO.setRatingList(ratingDTOList);
        }
        if (avgRating.getBody().getAverageRating() != null) {
            System.out.println("avgRating.getBody()).getAverageRating() ==> " + avgRating.getBody().getAverageRating());
            mediaDTO.setAvgRating(Double.parseDouble(String.format("%.2f", Objects.requireNonNull(avgRating.getBody()).getAverageRating())));
        }
        return mediaDTO;
    }

    @Override
    public MediaDTO update(Long id, MediaDTO mediaDTO) {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new RuntimeException("No media found"));
        if (media.getMediaType().equals("movie")) {
            Movie movie = modelMapper.map(media, Movie.class);
            movie.setName(mediaDTO.getName());
            movie.setReleasedYear(mediaDTO.getReleasedYear());
            movie.setGenre(mediaDTO.getGenre());
            movie.setDirector(mediaDTO.getDirector());
            movie.setActor(mediaDTO.getActor());
            movie.setDuration(mediaDTO.getDuration());
            movie.setGrossIncome(mediaDTO.getGrossIncome());
            movie.setAvgRating(mediaDTO.getAvgRating());

            Media mediaRepo = mediaRepository.save(movie);
            return modelMapper.map(mediaRepo, MediaDTO.class);
        } else {
            TvSeries tvSeries = modelMapper.map(media, TvSeries.class);
            tvSeries.setName(mediaDTO.getName());
            tvSeries.setReleasedYear(mediaDTO.getReleasedYear());
            tvSeries.setGenre(mediaDTO.getGenre());
            tvSeries.setDirector(mediaDTO.getDirector());
            tvSeries.setActor(mediaDTO.getActor());
            tvSeries.setDuration(mediaDTO.getDuration());
            tvSeries.setSeasons(mediaDTO.getSeasons());
            tvSeries.setAvgRating(mediaDTO.getAvgRating());

            Media mediaRepo = mediaRepository.save(tvSeries);
            return modelMapper.map(mediaRepo, MediaDTO.class);
        }
    }

    @Override
    public MediaDTO delete(Long id) {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new RuntimeException("No media found"));
        mediaRepository.delete(media);
        KafkaMediaDto kafkaMediaDTO = new KafkaMediaDto(media.getId());
        kafkaTemplate.send(movieTopic, kafkaMediaDTO);
        return modelMapper.map(media, MediaDTO.class);
    }

    @Override
    public List<MediaDTO> getAllByUserId(String userId) {
        List<Media> mediaList = mediaRepository.findAllByUserId(userId);
        if (mediaList.isEmpty()) {
            throw new RuntimeException("Sorry, media not found");
        }
        List<MediaDTO> mediaDTOList = mediaList.stream().map(
                media -> {
                    MediaDTO mediaDTO = modelMapper.map(media, MediaDTO.class);
                    return mediaDTO;
                }
        ).toList();
        return mediaDTOList;
    }

    private List<CommentDTO> getDefaultCommentList() {
        return new ArrayList<>();
    }

    private List<RatingDTO> getDefaultRatingList() {
        return new ArrayList<>();
    }

    private ResponseEntity<AvgRatingDto> getDefaultAverageRating(long id) {
        AvgRatingDto avgRatingDto = new AvgRatingDto();
        avgRatingDto.setMediaId(id);
        avgRatingDto.setAverageRating(1.22);
        return new ResponseEntity<>(avgRatingDto, HttpStatus.OK);
    }
}

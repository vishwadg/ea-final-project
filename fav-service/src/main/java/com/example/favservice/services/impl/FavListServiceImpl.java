package com.example.favservice.services.impl;

import com.example.favservice.entities.FavList;
import com.example.favservice.repositories.FavListRepository;
import com.example.favservice.services.FavListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavListServiceImpl implements FavListService {

    private final FavListRepository favListRepository;

    @Override
    public List<FavList> getAll() {
        log.info("Getting All FavLists");
        return favListRepository.findAll();
    }

    @Override
    public FavList getOne(Long id) {
        FavList favList = favListRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"));
        log.info("Getting FavList: {}", favList);
        return favList;
    }

    @Override
    public FavList save(FavList payload) {
        payload.setCreatedStamp(LocalDateTime.now());
        FavList save = favListRepository.save(payload);
        log.info("Saving FavList: {}", save);
        return save;
    }

    @Override
    public FavList update(FavList payload) {
        payload.setCreatedStamp(LocalDateTime.now());
        FavList save = favListRepository.save(payload);
        log.info("Saving FavList: {}", save);
        return save;
    }

    @Override
    public FavList delete(Long id) {
        FavList fav = favListRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"));
        favListRepository.deleteById(id);
        log.info("Deleting FavList of ID: {}", id);
        return fav;
    }
}

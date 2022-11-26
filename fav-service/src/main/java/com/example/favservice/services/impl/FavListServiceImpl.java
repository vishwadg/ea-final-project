package com.example.favservice.services.impl;

import com.example.favservice.entities.FavList;
import com.example.favservice.repositories.FavListRepository;
import com.example.favservice.services.FavListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavListServiceImpl implements FavListService {

    private final FavListRepository favListRepository;

    @Override
    public List<FavList> getAll() {
        return favListRepository.findAll();
    }

    @Override
    public FavList getOne(Long id) {
        return favListRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"));
    }

    @Override
    public FavList save(FavList payload) {
        payload.setCreatedStamp(LocalDateTime.now());
        return favListRepository.save(payload);
    }

    @Override
    public FavList update(FavList payload) {
        payload.setCreatedStamp(LocalDateTime.now());
        return favListRepository.save(payload);
    }

    @Override
    public FavList delete(Long id) {
        FavList fav = favListRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"));
        favListRepository.deleteById(id);
        return fav;
    }
}

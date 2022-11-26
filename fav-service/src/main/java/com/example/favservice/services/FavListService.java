package com.example.favservice.services;

import com.example.favservice.entities.FavList;

import java.util.List;

public interface FavListService {
    List<FavList> getAll();

    FavList getOne(Long id);

    FavList save(FavList payload);

    FavList update(FavList payload);

    FavList delete(Long id);
}

package com.example.favservice.controllers;

import com.example.favservice.entities.FavList;
import com.example.favservice.services.FavListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavListController {

    private final FavListService favListService;

    @GetMapping
    public List<FavList> getAll(){
        return favListService.getAll();
    }

    @GetMapping("/{id}")
    public FavList getOne(@PathVariable Long id){
        return favListService.getOne(id);
    }

    @PostMapping
    public FavList create(@RequestBody FavList payload){
        return favListService.save(payload);
    }

    @PutMapping
    public FavList update(@RequestBody FavList payload){
        return favListService.update(payload);
    }

    @DeleteMapping("/{id}")
    public FavList delete(@PathVariable Long id){
        return favListService.delete(id);
    }

}

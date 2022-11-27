package com.example.favservice.repositories;

import com.example.favservice.entities.FavList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavListRepository extends JpaRepository<FavList, Long> {
}

package com.example.demo1234.repository;

import com.example.demo1234.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games,Integer> {
}

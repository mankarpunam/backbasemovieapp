package com.example.backbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backbase.data.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
  
}
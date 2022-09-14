package com.example.backbase.repository;

import com.example.backbase.data.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDetailRepository extends JpaRepository<MovieDetails, Long> {


    public List<MovieDetails> findByNomineeAndWonAndCategory(String nominee , String won,String category);




}

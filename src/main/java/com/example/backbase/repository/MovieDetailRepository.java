package com.example.backbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backbase.data.MovieDetails;

@Repository
public interface MovieDetailRepository extends JpaRepository<MovieDetails, Long> {

	public MovieDetails findFirstByNomineeAndCategory(String nominee, String category);

}

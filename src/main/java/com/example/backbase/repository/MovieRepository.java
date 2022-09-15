package com.example.backbase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieWithRatings;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByMovieId(Long movieId);

    @Query(name = "find_top_movies", nativeQuery = true)
    public List<MovieWithRatings> getTopRatedMovies(@Param("dataLimit") Integer dataLimit);
}

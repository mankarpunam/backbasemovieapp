package com.example.backbase.repository;

import com.example.backbase.data.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByMovieId(Long movieId);

    @Query(value = "select * from movie where rating IS NOT NULL and movie_name IS NOT NULL group by movie_id order by rating desc limit 10", nativeQuery = true)
    public List<Movie> getTopRatedMovies();
}

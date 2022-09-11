package com.example.backbase.repository;

import com.example.backbase.data.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDetailRepository extends JpaRepository<MovieDetails, Long> {
    //@Query(value = "select a.id, a.category, a.additional_info, a.nominee, a.won ,a.year,a.rating from academy_awards a where a.won='YES'", nativeQuery = true)
    @Query(value = "select * from academy_awards a where a.won='YES'", nativeQuery = true)
    public List<MovieDetails> getOscarWonMovie();


    @Query(value = "select * from academy_awards where won = 'YES' and rating IS NOT NULL order by rating DESC limit 10 ", nativeQuery = true)
    List<MovieDetails> findTopRatedMovies();
}

package com.example.backbase.repository;

import com.example.backbase.data.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMovieId(Long movieId);

    @Query(value = "select m.movie_name,r.rating,m.movie_id from movie m left join rating r on r.movie_id= m.movie_id order by r.rating desc NULLS LAST limit 10", nativeQuery = true)
    public List<Rating> getTopRatedMovies();
}
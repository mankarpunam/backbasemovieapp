package com.example.backbase.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Generated
@Table(name = "movie")
@Entity
public class Movie implements Serializable {
    private static final long serialVersionUID = -8072920174744372780L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name")
    private String movieName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private List<Rating> rating;

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    /* @Min(0)
    @Max(5)
    @Column(name = "rating")
    private Long rating;

    @Column(name = "count")
    private Integer count;*/


}


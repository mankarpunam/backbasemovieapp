package com.example.backbase.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

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

    @Min(0)
    @Max(5)
    @Column(name = "rating")
    private Long rating;

    @Column(name = "count")
    private Integer count;


}


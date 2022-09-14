package com.example.backbase.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
@EqualsAndHashCode
@Table(name = "rating")
@Entity
public class Rating {
    private static final long serialVersionUID = -8072920174744372780L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "movie_id")
    private Long movieId;

    @Min(0)
    @Max(5)
    @Column(name = "rating")
    private Long rating;

    @Column(name = "count")
    private Integer count;
}

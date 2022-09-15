package com.backbase.movie.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class MovieWithRatings implements Serializable {
    private static final long serialVersionUID = -8072920174744372780L;
    private Long movieId;
    private String movieName;
    private Double avgRating;
}


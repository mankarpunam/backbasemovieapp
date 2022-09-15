package com.example.backbase.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieWithRatings implements Serializable {
    private static final long serialVersionUID = -8072920174744372780L;
    private Long movieId;
    private String movieName;
    private Double avgRating;
}


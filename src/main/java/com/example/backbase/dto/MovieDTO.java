package com.example.backbase.dto;

import io.micrometer.core.lang.Nullable;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
@Generated
@EqualsAndHashCode
public class MovieDTO {
    private Long movieId;
    private String movieName;
    @Min(0)
    @Max(5)
    private Long rating;
    private Integer count;
}

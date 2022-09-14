package com.example.backbase.dto;

import io.micrometer.core.lang.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
@EqualsAndHashCode
public class MovieDTO {
    private Integer movie_id;
    private String movie_name;
}

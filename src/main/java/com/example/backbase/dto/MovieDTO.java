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
    private Integer id;
    private String year;
    private String category;
    private String nominee;
    private String additional_info;
    private String won;
    private Integer rating;
}

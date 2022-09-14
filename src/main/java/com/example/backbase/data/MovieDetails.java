package com.example.backbase.data;


import io.micrometer.core.lang.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@Table(name = "academy_awards")
@Entity
public class MovieDetails implements Serializable {
    private static final long serialVersionUID = -8072920174744372780L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academy_id")
    private Integer academyId;

    @Column(name = "win_year")
    private String year;

    @Column(name = "category")
    private String category;

    @Column(name = "nominee")
    private String nominee;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "won")
    private String won;

}


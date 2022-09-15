package com.example.backbase.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@Generated
@Table(name = "movie")
@Entity
@NamedNativeQuery(name = "find_top_movies", 
	query = "SELECT " + " m.movie_id as movieId,"
		+ " m.movie_name as movieName, " + " avg(r.rating) as avgRating"
		+ " from movie m left join rating r on r.movie_id= m.movie_id" + " group by m.movie_id"
		+ " order by avg(r.rating) desc NULLS LAST limit :dataLimit", resultSetMapping = "top_movies")
@SqlResultSetMapping(name = "top_movies", classes = @ConstructorResult(targetClass = MovieWithRatings.class, 
	columns = {
		@ColumnResult(name = "movieId", type = Long.class), 
		@ColumnResult(name = "movieName", type = String.class),
		@ColumnResult(name = "avgRating", type = Double.class) }))
public class Movie implements Serializable {
	private static final long serialVersionUID = -8072920174744372780L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long movieId;
	@Column(name = "movie_name")
	private String movieName;
}

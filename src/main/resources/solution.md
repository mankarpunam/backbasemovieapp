Database table

1. Created academy table which will be master table which stores the movie data.(all data from csv)
2. Movie table which will store movie data like movie id and movie name
   I have copied the best picture category movie data in movie table for now.
3. Rating table which will store rating data which include rating id , movie id and rating

Three Api based on description I have created

1. http://${env}:8086/movie/bestPicture?title={value}
   which will fetch the best picture based on title provided by user
   Result: In result user will get the message

* if movie title is won oscar for best picture
  The movie True Grit has won the award!
* if not user will get message
  The movie True Grit has not won the award!

2. User can post the rating for Best picture
   http://${env}:8086/movie/rating/{ratingId}
   with RequestBody
   {
   "rating":5
   }
   Result: {
   "ratingId": 1,
   "movieId": 4,
   "rating": 5
   }
3. User can see top 10 best picture oscar won movie which is top rated
   http://${env}:8086/movie/topRatedMovies?dataLimit={value}

Result :User will get the top rated 10 movies if it is rated
[
{
"movieId": 4,
"movieName": "All Quiet on the Western Front",
"avgRating": 5.0
}
]


4. I have added liquibase script when user will build the project the table will be automatically created .
refer : db.changelog 

5. I have implemented profile based application so it is easy to do configuration by environment specific 
6. Added sonar coverage confguration to see the coverage of code
7. Added open api specification for the api
http://localhost:8086/swagger-ui/index.html#/



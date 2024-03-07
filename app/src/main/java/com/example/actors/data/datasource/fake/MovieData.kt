package com.example.actors.data.datasource.fake

import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.FavoriteMovie
import com.example.actors.data.model.Genre
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.Trailer


fun fakeMovieList(): MutableList<Movie> {
    val movies = mutableListOf<Movie>()
    listOf(
        "Pulp Fiction",
        "Kill Bill: Volume 1",
        "Taxi Driver",
        "Goodfellas",
        "Kill Bill: Volume 2",
        "Heat",
        "Scarface",
        "Scent of a Woman",
        "The Devil's Advocate",
        "The Prestige",
        "Prisoners",
        "American Psycho",
        "Logan",
    ).forEachIndexed { index, name ->
        movies.add(Movie(index, name, "", ""))
    }

    return movies
}

val upcomingMoviesList = listOf(
    Movie(
        movieId = 363736, movieName = "Oppenheimer", posterPathUrl = "", bannerUrl = ""
    ),
    Movie(
        movieId = 123434, movieName = "Dune", posterPathUrl = "", bannerUrl = ""
    )
)

val nowPlayingMoviesList = listOf(
    Movie(
        movieId = 157336, movieName = "Interstellar", posterPathUrl = "", bannerUrl = ""
    ),
    Movie(
        movieId = 244786, movieName = "Whiplash", posterPathUrl = "", bannerUrl = ""
    )
)
fun fakeFavoriteMovieList(): MutableList<FavoriteMovie> {
    val movies = mutableListOf<FavoriteMovie>()
    movies.forEachIndexed { index, name ->
        movies.add(FavoriteMovie(index, "Interstellar", "posterUrl", "bannerUrl"))
    }
    return movies
}

val fakeMovieDetail = MovieDetail(
    movieId = 12345,
    movieTitle = "Pulp Fiction",
    banner = "banner-url.jpeg",
    budget = "20 Million",
    originalLanguage = "English",
    overview = "In the realm of underworld, a series of incidents intertwines the lives of two Los Angeles mobsters, a gangster's wife, a boxer and two small-time criminals.",
    popularity = 99.0,
    poster = "poster-url.jpeg",
    productionCompanies = listOf("Whatever, WhoCares"),
    releaseDate = "1994",
    revenue = 21400000,
    runtime = 154,
    status = "Released",
    tagline = "You won't know the facts until you've seen the fiction.",
    voteAverage = 99.0,
    genres = listOf(Genre(1, "Thriller"), Genre(2, "Crime")),

)

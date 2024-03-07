package com.example.actors.data.model

import androidx.compose.runtime.Stable
import com.example.actors.data.datasource.database.entity.FavoriteMoviesEntity

data class FavoriteMovie(
    @Stable val movieId: Int,
    val movieName:String,
    val posterPathUrl: String,
    val bannerUrl:String
)
fun FavoriteMovie.movieAsDatabaseModel(): FavoriteMoviesEntity{
    return FavoriteMoviesEntity(
        movieId = this.movieId,
        movieName = this.movieName,
        moviePosterUrl = this.posterPathUrl,
        movieBanner = this.bannerUrl
    )
}

fun List<FavoriteMoviesEntity>.movieAsDomainModel(): List<FavoriteMovie>{
    return map {
        FavoriteMovie(
            movieId = it.movieId,
            movieName = it.movieName,
            posterPathUrl = it.moviePosterUrl,
            bannerUrl = it.movieBanner
        )
    }
}




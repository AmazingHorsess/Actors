package com.example.actors.data.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName

@Immutable
    data class MovieDetail(
    @Stable val movieId: Int,
    @SerialName("original_title") val movieTitle: String,
    @SerialName("backdrop_path") val banner: String,
    @SerialName("budget") val budget: String,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val poster: String,
    @SerialName("production_companies") val productionCompanies: List<String>,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("revenue") val revenue: Long,
    @SerialName("runtime") val runtime: Int,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String,
    @SerialName("vote_average") val voteAverage: Double
    )
fun MovieDetail.toFavoriteMovie() = FavoriteMovie(
    movieId = this.movieId,
    movieName = this.movieTitle,
    posterPathUrl = this.poster,
    bannerUrl = this.banner
)


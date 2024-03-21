package com.example.actors.data.datasource.network.retrofit.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorCastResponse(
    @SerialName("cast") val cast: List<CastItem>? = null,

    @SerialName("id") val castId: Int? = null,



)
@Serializable
data class CastItem(
    @SerialName("adult") val adult: Boolean,

    @SerialName("backdrop_path") val backdropPath: String?,

    @SerialName("genre_ids") val genreIds: List<Int>,

    @SerialName("id") val id: Int,

    @SerialName("original_language") val originalLanguage: String,

    @SerialName("original_title") val originalTitle: String,

    @SerialName("overview") val overview: String,

    @SerialName("popularity") val popularity: Double,

    @SerialName("poster_path") val posterPath: String?,

    @SerialName("release_date") val releaseDate: String,

    @SerialName("title") val title: String,

    @SerialName("video") val video: Boolean,

    @SerialName("vote_average") val voteAverage: Double,

    @SerialName("vote_count") val voteCount: Int,

    @SerialName("character") val character: String,

    @SerialName("credit_id") val creditId: String,

    @SerialName("order") val order: Int

)

@Serializable
data class Genre(
    @SerialName("id") @Stable val genreId:Int,
    @SerialName("name") val genreName:String
)

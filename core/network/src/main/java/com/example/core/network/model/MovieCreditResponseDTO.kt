package com.example.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditResponseDTO (

    @SerialName("cast") val cast: ArrayList<Cast> = arrayListOf(),
    @SerialName("crew") val crew: ArrayList<Crew> = arrayListOf(),
    @SerialName("id") val id: Int? = null

)
@Serializable
data class Cast (

    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("genre_ids") val genreIds: ArrayList<Int> = arrayListOf(),
    @SerialName("id") val id: Int? = null,
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("video") val video: Boolean? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null,
    @SerialName("character") val character: String? = null,
    @SerialName("credit_id") val creditId: String? = null,
    @SerialName("order") val order: Int? = null

)
@Serializable
data class Crew(

    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("genre_ids") val genreIds: ArrayList<Int> = arrayListOf(),
    @SerialName("id") val id: Int? = null,
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("video") val video: Boolean? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null,
    @SerialName("credit_id") val creditId: String? = null,
    @SerialName("department") val department: String? = null,
    @SerialName("job") val job: String? = null

)
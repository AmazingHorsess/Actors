package com.example.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class TrendingPersonResponseDTO(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<TrendingPersonsResultsDTO>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)


@Serializable
data class TrendingPersonsResultsDTO (
    @SerialName("adult") val adult: Boolean,
    @SerialName("id" ) val id: Int,
    @SerialName("name" ) val name: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("gender") val gender: Int,
    @SerialName("known_for_department") val knownForDepartment : String,
    @SerialName("profile_path") val profilePath: String,
    @SerialName("known_for") val knownFor: List<KnownForTrendingDTO>

)
@Serializable
data class KnownForTrendingDTO(
    @SerialName("adult")
    val adult: Boolean = false, // false
    @SerialName("backdrop_path")
    val backdropPath: String? = null, // /ilRyazdMJwN05exqhwK4tMKBYZs.jpg
    @SerialName("first_air_date")
    val firstAirDate: String? = null, // 2019-06-16
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("id")
    val id: Int = 0, // 335984
    @SerialName("media_type")
    val mediaType: String = "", // movie
    @SerialName("name")
    val name: String? = null, // Euphoria
    @SerialName("origin_country")
    val originCountry: List<String>? = null,
    @SerialName("original_language")
    val originalLanguage: String = "", // en
    @SerialName("original_name")
    val originalName: String? = null, // Euphoria
    @SerialName("original_title")
    val originalTitle: String? = null, // Blade Runner 2049
    @SerialName("overview")
    val overview: String = "", // Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos. K's discovery leads him on a quest to find Rick Deckard, a former LAPD blade runner who has been missing for 30 years.
    @SerialName("popularity")
    val popularity: Double = 0.0, // 79.571
    @SerialName("poster_path")
    val posterPath: String = "", // /gajva2L0rPYkEWjzgFlBXCAVBE5.jpg
    @SerialName("release_date")
    val releaseDate: String? = null, // 2017-10-04
    @SerialName("title")
    val title: String? = null, // Blade Runner 2049
    @SerialName("video")
    val video: Boolean? = null, // false
    @SerialName("vote_average")
    val voteAverage: Double = 0.0, // 7.531
    @SerialName("vote_count")
    val voteCount: Int = 0 // 11771
)

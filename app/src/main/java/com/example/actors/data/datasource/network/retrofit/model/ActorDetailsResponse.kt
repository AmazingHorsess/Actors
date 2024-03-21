package com.example.actors.data.datasource.network.retrofit.model

import kotlinx.serialization.SerialName

data class ActorDetailsResponse (
    @SerialName("adult") val adult: Boolean,
    @SerialName("also_known_as") val alsoKnownAs: List<String>,
    @SerialName("biography") val biography: String,
    @SerialName("birthday") val birthday: String,
    @SerialName("deathday") val deathday: String?,
    @SerialName("gender") val gender: Int,
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Int,
    @SerialName("imdb_id") val imdbId: String,
    @SerialName("known_for_department") val knownForDepartment: String,
    @SerialName("name") val name: String,
    @SerialName("place_of_birth") val placeOfBirth: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("profile_path") val profilePath: String?
)

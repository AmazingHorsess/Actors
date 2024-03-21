package com.example.actors.data.datasource.network.retrofit.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorItem (

    @SerialName("id") @Stable val actorId: Int,
    @SerialName("name") val actorName: String,
    @SerialName("profile_path") val profileUrl: String,
    @SerialName("biography") val biography: String,
    @SerialName("birthday") val dateOfBirth: String,
    @SerialName("place_of_birth") val placeOfBirth: String,
    @SerialName("popularity")val popularity: Double

)
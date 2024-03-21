package com.example.actors.data.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.actors.data.datasource.database.entity.FavoriteActorsEntity
import kotlinx.serialization.SerialName

@Immutable
data class ActorDetail(
   @SerialName("id") @Stable val actorId: Int,
   @SerialName("name") val actorName: String,
   @SerialName("profile_path") val profileUrl: String,
    @SerialName("biography") val biography: String,
    @SerialName("birthday") val dateOfBirth: String,
    @SerialName("place_of_birth") val placeOfBirth: String,
    @SerialName ("popularity")val popularity: Double
)

fun ActorDetail.toFavoriteActor() = FavoriteActor(
    actorId = this.actorId,
    actorName = this.actorName,
    profileUrl = this.profileUrl,
    placeOfBirth = this.placeOfBirth
)




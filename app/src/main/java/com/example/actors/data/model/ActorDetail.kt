package com.example.actors.data.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.actors.data.datasource.database.entity.FavoriteActorsEntity

@Immutable
data class ActorDetail(
    @Stable val actorId: Int,
    val actorName: String,
    val profileUrl: String,
    val biography: String,
    val dateOfBirth: String,
    val placeOfBirth: String,
    val popularity: Double
)

fun ActorDetail.toFavoriteActor() = FavoriteActor(
    actorId = this.actorId,
    actorName = this.actorName,
    profileUrl = this.profileUrl,
    placeOfBirth = this.placeOfBirth
)




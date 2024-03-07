package com.example.actors.data.model

import androidx.compose.runtime.Stable
import com.example.actors.data.datasource.database.entity.FavoriteActorsEntity

data class FavoriteActor(
    @Stable val actorId: Int,
    val actorName: String,
    val profileUrl: String,
    val placeOfBirth: String
)
fun FavoriteActor.actorAsDatabaseModel(): FavoriteActorsEntity {
    return FavoriteActorsEntity(
        actorId = this.actorId,
        actorName = this.actorName,
        actorProfileUrl = this.profileUrl,
        actorPlaceOfBirth = this.placeOfBirth
    )
}

fun List<FavoriteActorsEntity>.actorAsDomainModel(): List<FavoriteActor>{
    return map {
        FavoriteActor(
            actorId = it.actorId,
            actorName = it.actorName,
            profileUrl = it.actorProfileUrl,
            placeOfBirth = it.actorPlaceOfBirth

        )
    }
}

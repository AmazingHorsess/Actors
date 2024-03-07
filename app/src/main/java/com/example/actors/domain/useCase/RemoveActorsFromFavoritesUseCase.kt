package com.example.actors.domain.useCase

import com.example.actors.data.model.FavoriteActor
import com.example.actors.domain.repository.actor.ActorRepository
import javax.inject.Inject

class RemoveActorsFromFavoritesUseCase @Inject constructor(
    private val actorRepository: ActorRepository
) {
    suspend operator fun invoke(favoriteActor:FavoriteActor){
        actorRepository.deleteSelectedFavoriteActor(
            favoriteActor = favoriteActor
        )
    }
}
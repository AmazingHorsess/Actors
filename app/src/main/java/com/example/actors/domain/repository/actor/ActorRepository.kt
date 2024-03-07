package com.example.actors.domain.repository.actor

import androidx.lifecycle.LiveData
import com.example.actors.data.model.Actor
import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.Movie

interface ActorRepository {

    suspend fun getPopularActorsData():List<Actor>

    suspend fun getTrendingActorsData(): List<Actor>

    suspend fun getUpcomingMoviesData(): List<Movie>

    suspend fun getSelectedActorData(actorInt: Int): ActorDetail

    suspend fun getCastData(actorInt: Int): List<Movie>

    fun isFavoriteActor(actorId: Int): LiveData<Int>

    suspend fun addActorsToFavorite(favoriteActor: FavoriteActor)

    suspend fun deleteSelectedFavoriteActor(favoriteActor: FavoriteActor)

    fun getAllFavoriteActors(): LiveData<List<FavoriteActor>>
}
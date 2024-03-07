package com.example.actors.data.repository.actor

import androidx.lifecycle.LiveData
import com.example.actors.data.datasource.database.DatabaseDataSource
import com.example.actors.data.datasource.network.json.NetworkDataSource
import com.example.actors.data.model.Actor
import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.Movie
import com.example.actors.domain.repository.actor.ActorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActorRepositoryImpl @Inject constructor(
    private val stockJsonDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource
):ActorRepository {

    override suspend fun getPopularActorsData(): List<Actor> {
        return stockJsonDataSource.getPopularActorsData()
    }

    override suspend fun getTrendingActorsData(): List<Actor> {
        return stockJsonDataSource.getTrendingActorsData()
    }

    override suspend fun getUpcomingMoviesData(): List<Movie> {
        return stockJsonDataSource.getUpcomingMoviesData()
    }

    override suspend fun getSelectedActorData(actorInt: Int): ActorDetail {
        return stockJsonDataSource.getSelectedActorData(actorInt)
    }

    override suspend fun getCastData(actorInt: Int): List<Movie> {
        return stockJsonDataSource.getCastData(actorInt)
    }

    override fun isFavoriteActor(actorId: Int): LiveData<Int> {
        return databaseDataSource.checkIfActorIsFavorite(actorId)
    }

    override suspend fun addActorsToFavorite(favoriteActor: FavoriteActor) {
        return databaseDataSource.addActorToFavorites(favoriteActor)
    }

    override suspend fun deleteSelectedFavoriteActor(favoriteActor: FavoriteActor) {
        databaseDataSource.deleteSelectedFavoriteActor(favoriteActor)
    }

    override fun getAllFavoriteActors(): LiveData<List<FavoriteActor>> {
        return databaseDataSource.getAllFavoriteActors()
    }
}
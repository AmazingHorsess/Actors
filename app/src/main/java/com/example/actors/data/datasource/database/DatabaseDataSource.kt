package com.example.actors.data.datasource.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.actors.data.datasource.database.entity.MovieTrackingEntity
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.FavoriteMovie
import com.example.actors.data.model.actorAsDatabaseModel
import com.example.actors.data.model.actorAsDomainModel
import com.example.actors.data.model.movieAsDatabaseModel
import com.example.actors.data.model.movieAsDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseDataSource @Inject constructor(
    private val database: AppDatabase
){

    suspend fun insertMovieTracking(movieTracking: MovieTrackingEntity) = database.movieTrackingDao.insertMovieTracking(movieTracking)

    suspend fun getAllMovieTrackings(): List<MovieTrackingEntity> = database.movieTrackingDao.getAllMovieTrackings()

    suspend fun getMovieTrackingById(id: Int): MovieTrackingEntity? = database.movieTrackingDao.getMovieTrackingById(id)

    suspend fun deleteMovieTrackingById(id: Int) = database.movieTrackingDao.deleteMovieTrackingById(id)


    fun getAllFavoriteMovies(): LiveData<List<FavoriteMovie>>{
        val allFavoriteMovies = database.favoriteMoviesDao.getAllFavoriteMovies()
        return allFavoriteMovies.map { favEntityList ->
            favEntityList.movieAsDomainModel()

        }
    }

    fun getAllFavoriteActors(): LiveData<List<FavoriteActor>>{
        val allFavoriteActors = database.favoriteActorDao.getAllFavoriteActors()
        return allFavoriteActors.map { favEntityList->
            favEntityList.actorAsDomainModel()
        }
    }

    fun checkIfActorIsFavorite(actorId:Int) = database.favoriteActorDao.checkIfActorIsFavorite(actorId)

    fun checkIfMovieIsFavorite(movieId:Int) = database.favoriteMoviesDao.checkIfMovieIsFavorite(movieId)

    suspend fun addMovieToFavorites(movie:FavoriteMovie) = withContext(Dispatchers.IO){
        with(movie.movieAsDatabaseModel()){
            database.favoriteMoviesDao.addMovieToFavorites(favoriteMoviesEntity = this)
        }
    }

    suspend fun addActorToFavorites(favoriteActor: FavoriteActor) = withContext(Dispatchers.IO){
        with(favoriteActor.actorAsDatabaseModel()){
            database.favoriteActorDao.addActorToFavorites(favoriteActorsEntity = this)
        }
    }


    suspend fun deleteSelectedFavoriteMovie(movie:FavoriteMovie) = withContext(Dispatchers.IO){
        with(movie.movieAsDatabaseModel()){
            database.favoriteMoviesDao.deleteSelectedFavoriteMovie(favoriteMoviesEntity = this)
        }
    }

    suspend fun deleteSelectedFavoriteActor(favoriteActor: FavoriteActor) = withContext(Dispatchers.IO){
        with(favoriteActor.actorAsDatabaseModel()){
            database.favoriteActorDao.deleteSelectedFavoriteActor(favoriteActorsEntity = this)
        }
    }
}
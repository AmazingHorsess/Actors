package com.example.actors.domain.repository.movies

import androidx.lifecycle.LiveData
import com.example.actors.data.model.Cast
import com.example.actors.data.model.FavoriteMovie
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.PagedResponse
import com.example.actors.data.model.Trailer
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun  getNowPlayingMoviesData(page:Int): PagedResponse<Movie>

    suspend fun getSelectedMovieData(movieId:Int): MovieDetail

    suspend fun getUpcomingMovie(): List<Movie>

    suspend fun getSimilarMoviesByIdData(movieId:Int) : List<Movie>

    suspend fun getRecommendedMoviesByIdData(movieId: Int): List<Movie>

    suspend fun getMovieCastByIdData(movieId: Int): List<Cast>

    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>>

    fun isFavoriteMovie(movieId: Int): LiveData<Int>

    suspend fun addMovieToFavorites(movie: FavoriteMovie)

    suspend fun deleteSelectedFavoriteMovie(movie: FavoriteMovie)

    suspend fun getMovieProvidersData(movieId: Int): MovieProvider

    suspend fun getMovieTrailer(movieId: Int): List<Trailer>


}
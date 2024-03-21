package com.example.actors.data.repository.movie

import androidx.lifecycle.LiveData
import com.example.actors.data.datasource.database.DatabaseDataSource
import com.example.actors.data.datasource.network.json.NetworkDataSource
import com.example.actors.data.model.Cast
import com.example.actors.data.model.FavoriteMovie
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.PagedResponse
import com.example.actors.data.model.Trailer
import com.example.actors.domain.repository.movies.MovieRepository
import com.example.actors.domain.repository.movies.MovieTrailerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val stockJsonDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource
) :MovieRepository {

    override suspend fun getNowPlayingMoviesData(page: Int): PagedResponse<Movie> {
        return stockJsonDataSource.getNowPlayingMoviesData(page)
    }

    override suspend fun getSelectedMovieData(movieId: Int): MovieDetail {
        return stockJsonDataSource.getSelectedMovieData(movieId)
    }

    override suspend fun getUpcomingMovie(): List<Movie> {
        return stockJsonDataSource.getUpcomingMoviesData()
    }


    override suspend fun getMovieTrailer(movieId: Int): List<Trailer> {
        return stockJsonDataSource.getMovieTrailersData(movieId)
    }

    override suspend fun getSimilarMoviesByIdData(movieId: Int): List<Movie> {
        return stockJsonDataSource.getSimilarMoviesByIdData(movieId)
    }

    override suspend fun getRecommendedMoviesByIdData(movieId: Int): List<Movie> {
        return stockJsonDataSource.getRecommendedMoviesByIdData(movieId)
    }

    override suspend fun getMovieCastByIdData(movieId: Int): List<Cast> {
        return stockJsonDataSource.getMovieCastByIdData(movieId)
    }

    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return flow {
            databaseDataSource.getAllFavoriteMovies()
        }
    }



    override fun isFavoriteMovie(movieId: Int): LiveData<Int> {
        return databaseDataSource.checkIfMovieIsFavorite(movieId)
    }

    override suspend fun getMovieProvidersData(movieId: Int): MovieProvider {
        return stockJsonDataSource.getMovieProvidersData(movieId)
    }



    override suspend fun addMovieToFavorites(movie: FavoriteMovie) {
        return databaseDataSource.addMovieToFavorites(movie)
    }

    override suspend fun deleteSelectedFavoriteMovie(movie: FavoriteMovie) {
        return databaseDataSource.deleteSelectedFavoriteMovie(movie)
    }


}
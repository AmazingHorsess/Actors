package com.example.actors.data.repository.movie

import com.example.actors.data.datasource.network.json.NetworkDataSource
import com.example.actors.data.model.Trailer
import com.example.actors.domain.repository.movies.MovieTrailerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieTrailerRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
): MovieTrailerRepository {
    override suspend fun getMovieTrailerUrl(movieId: Int) : List<Trailer> {
        return networkDataSource.getMovieTrailersData(movieId)

    }



    override suspend fun getMovieTrailerThumbnail(trailerId: String) {
       return networkDataSource.getMovieTrailerThumbnail(trailerId)
    }

}
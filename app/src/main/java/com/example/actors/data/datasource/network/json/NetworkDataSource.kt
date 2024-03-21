package com.example.actors.data.datasource.network.json

import com.example.actors.data.datasource.network.RequestUrls
import com.example.actors.data.model.Actor
import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.Cast
import com.example.actors.data.model.Movie
import com.example.actors.data.model.MovieDetail
import com.example.actors.data.model.MovieProvider
import com.example.actors.data.model.PagedResponse
import com.example.actors.data.model.Trailer
import com.example.actors.utils.NetworkQueryUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(
    private val requestUrls: RequestUrls,
    private val jsonData: JsonRemoteData,
    private val queryUtils: NetworkQueryUtils
    )  {
    /*** @return the result of latest list of all popular actors fetched from the network.*/
    suspend fun getPopularActorsData(): Flow<Actor> {
        withContext(Dispatchers.IO) {
            val requestUrl = requestUrls.getPopularActorsUrl()

            val response = queryUtils.getResponseFromHttpUrl(requestUrl)
           val result = jsonData.fetchActorsJsonData(response)


        }
        return emptyFlow()


    }

    /** @return the result of latest list of all trending actors fetched from the network. */
    suspend fun getTrendingActorsData(): List<Actor> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getTrendingActorsUrl()
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchActorsJsonData(response)
    }

    /**
     * @param actorId user selected actor.
     * @return the result of details of actor which fetched from the network.
     */
    suspend fun getSelectedActorData(
        actorId: Int
    ): ActorDetail = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getActorDetailsUrl(actorId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchActorDetailsJsonData(response)
    }

    /**
     * @param actorId user selected actor.
     * @return the result of list of movies which actor was casted with from the network.
     */
    suspend fun getCastData(
        actorId: Int
    ): List<Movie> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getCastDetailsUrl(actorId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchCastDetailsJsonData(response)
    }

    suspend fun getSelectedMovieData(
        movieId: Int
    ): MovieDetail = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getMovieDetailsUrl(movieId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchMovieDetailsJsonData(response)
    }


    /**
     * @param movieId for finding similar movies.
     * @return the result of list of movies which are based on current movie id.
     */
    suspend fun getSimilarMoviesByIdData(
        movieId: Int
    ): List<Movie> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getSimilarMoviesUrl(movieId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchSimilarAndRecommendedMoviesJsonData(response)
    }

    /**
     * @param movieId for finding recommended movies.
     * @return the result of list of movies which are based on current movie id.
     */
    suspend fun getRecommendedMoviesByIdData(
        movieId: Int
    ): List<Movie> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getRecommendedMoviesUrl(movieId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchSimilarAndRecommendedMoviesJsonData(response)
    }

    /**
     * @param movieId for finding cast & crew.
     * @return the result of list of cast which are based on current movie id.
     */
    suspend fun getMovieCastByIdData(
        movieId: Int
    ): List<Cast> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getMovieCastUrl(movieId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchMovieCastByIdJsonData(response)
    }

    suspend fun getUpcomingMoviesData(): List<Movie> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getUpcomingMoviesUrl()
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchUpcomingMoviesJsonData(response)
    }

    suspend fun getMovieProvidersData(
        movieId: Int
    ): MovieProvider = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getMovieProviderUrl(movieId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchMovieProvidersJsonData(response)
    }

    suspend fun getNowPlayingMoviesData(
        page: Int
    ): PagedResponse<Movie> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getNowPlayingMoviesUrl(page)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchNowPlayingMoviesJsonData(response)
    }

    /**
     * @param query user submitted query to search actors.
     * @return the result of list of actors with matching query fetched from the network.
     */
    suspend fun getSearchableActorsData(
        query: String
    ): List<Actor> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getSearchActorsUrl(query)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchActorsJsonData(response)
    }

    suspend fun getSearchableMoviesData(
        query: String
    ): List<Movie> = withContext(Dispatchers.IO) {
        val requestUrl = requestUrls.getSearchMoviesUrl(query)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        jsonData.fetchMoviesJsonData(response)
    }


    suspend fun getMovieTrailersData(
        movieId: Int
    ): List<Trailer> = withContext(Dispatchers.IO) {
        val trailerUrl = requestUrls.getMovieTrailerUrl(movieId)
        val response = queryUtils.getResponseFromHttpUrl(trailerUrl)
        jsonData.fetchMovieTrailersJsonData(response)
    }

    suspend fun getMovieTrailerThumbnail(
        trailerId: String
    ){
        withContext(Dispatchers.IO){
            val requestUrls = requestUrls.buildMovieTrailerThumbnail(trailerId)
            val response = queryUtils.getResponseFromHttpUrl(requestUrls)
        }
    }



}
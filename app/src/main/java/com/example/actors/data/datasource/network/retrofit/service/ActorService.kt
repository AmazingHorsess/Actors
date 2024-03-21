package com.example.actors.data.datasource.network.retrofit.service

import com.example.actors.data.datasource.network.retrofit.model.ActorCastResponse
import com.example.actors.data.datasource.network.retrofit.model.ActorDetailsResponse
import com.example.actors.data.datasource.network.retrofit.model.PopularActorResponse
import com.example.actors.data.datasource.network.retrofit.model.TrendingActorResponse
import com.example.actors.data.model.Actor
import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.Cast
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ActorService {

    @GET("3/trending/person/{time_window}")
    suspend fun getTrendingActors(
        @Path("time_window") trendingDate: String = "week",
        @Query("page") page: Int?
    ): TrendingActorResponse

    @GET("3/person/popular")
    suspend fun getPopularActors(
        @Query("page") page: Int?
    ): PopularActorResponse

    @GET("3/person/{personId}/movie_credits")
    suspend fun getCastData(
        @Path("personId") actorId: Int
    ): Response<ActorCastResponse>

    @GET("3/person/{personId}")
    suspend fun getSelectedActor(
        @Path("personId") actorId: Int
    ): Response<ActorDetailsResponse>
}

@Serializable
sealed class NetworkResult<out T> {

    data class Success<out T>(val value: T) : NetworkResult<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val errorMessage: String?
    ) : NetworkResult<Nothing>()

    object Loading : NetworkResult<Nothing>()

}
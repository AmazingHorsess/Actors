package com.example.actors.domain.repository.actor


import androidx.paging.PagingData
import com.example.actors.data.datasource.network.retrofit.model.ActorCastResponse
import com.example.actors.data.datasource.network.retrofit.model.ActorDetailsResponse
import com.example.actors.data.datasource.network.retrofit.model.ActorItem
import com.example.actors.data.datasource.network.retrofit.model.PopularActorResponse
import com.example.actors.data.datasource.network.retrofit.model.TrendingActorResponse
import com.example.actors.data.datasource.network.retrofit.service.NetworkResult

import com.example.actors.data.model.Cast
import com.example.actors.data.model.FavoriteActor
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ActorRepository {

    //paging
    suspend fun getPopularActorsPagingData(): Flow<PagingData<ActorItem>>


    suspend fun getTrendingActorsPagingData(): Flow<PagingData<ActorItem>>

    //non-paging

    suspend fun popularActorsList(page:Int): Flow<NetworkResult<PopularActorResponse>>
    suspend fun trendingActorsList(page:Int): Flow<NetworkResult<TrendingActorResponse>>


    suspend fun getSelectedActorData(actorId: Int): Flow<NetworkResult<Response<ActorDetailsResponse>>>

    suspend fun getCastData(actorId: Int): Flow<NetworkResult<Response<ActorCastResponse>>>

    fun isFavoriteActor(actorId: Int): Flow<Int>

    suspend fun addActorsToFavorite(favoriteActor: FavoriteActor)

    suspend fun deleteSelectedFavoriteActor(favoriteActor: FavoriteActor)

    fun getAllFavoriteActors(): Flow<List<FavoriteActor>>
}
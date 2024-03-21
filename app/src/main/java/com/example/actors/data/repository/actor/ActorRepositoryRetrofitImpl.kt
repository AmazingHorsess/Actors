package com.example.actors.data.repository.actor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.actors.data.datasource.database.DatabaseDataSource
import com.example.actors.data.datasource.network.retrofit.model.ActorCastResponse
import com.example.actors.data.datasource.network.retrofit.model.ActorDetailsResponse
import com.example.actors.data.datasource.network.retrofit.model.ActorItem
import com.example.actors.data.datasource.network.retrofit.model.PopularActorResponse
import com.example.actors.data.datasource.network.retrofit.model.TrendingActorResponse
import com.example.actors.data.datasource.network.retrofit.service.ActorService
import com.example.actors.data.datasource.network.retrofit.service.NetworkResult
import com.example.actors.data.datasource.network.retrofit.util.Constants
import com.example.actors.data.datasource.network.retrofit.util.ResponseCodeManager
import com.example.actors.data.model.Actor
import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.Cast
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.paging.PopularActorSource
import com.example.actors.data.paging.TrendingActorSource
import com.example.actors.domain.repository.actor.ActorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActorRepositoryRetrofitImpl @Inject constructor(
    private val actorApiService:ActorService,
    private val databaseDataSource: DatabaseDataSource
): ActorRepository {
    override suspend fun getPopularActorsPagingData(): Flow<PagingData<ActorItem>> = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {PopularActorSource(actorApiService)}
    ).flow

    override suspend fun getTrendingActorsPagingData(): Flow<PagingData<ActorItem>> = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = { TrendingActorSource(actorApiService) }
    ).flow

    override suspend fun popularActorsList(page: Int): Flow<NetworkResult<PopularActorResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = actorApiService.getPopularActors(page)
                emit(NetworkResult.Success(response))
            } catch (throwable: Throwable){
                emit(
                    when(throwable){
                        is HttpException ->{
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()?.let { ResponseCodeManager.checkRetrofitApiResponse(it) }
                            )
                        }
                        is IOException ->{
                            NetworkResult.Failure(
                                true,
                                null,
                                null,
                                Constants.STS_DEFAULT
                            )
                        }
                        else -> {
                            NetworkResult.Failure(
                                false,
                                null,
                                null,
                                Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun trendingActorsList(page: Int): Flow<NetworkResult<TrendingActorResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = actorApiService.getTrendingActors(page = page)
                emit(NetworkResult.Success(response))
            } catch (throwable: Throwable){
                emit(
                    when(throwable){
                        is HttpException ->{
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()?.let { ResponseCodeManager.checkRetrofitApiResponse(it) }
                            )
                        }
                        is IOException ->{
                            NetworkResult.Failure(
                                true,
                                null,
                                null,
                                Constants.STS_DEFAULT
                            )
                        }
                        else -> {
                            NetworkResult.Failure(
                                false,
                                null,
                                null,
                                Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun getSelectedActorData(actorId: Int): Flow<NetworkResult<Response<ActorDetailsResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = actorApiService.getSelectedActor(actorId)
                emit(NetworkResult.Success(response))
            } catch (throwable: Throwable){
                emit(
                    when(throwable){
                        is HttpException ->{
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()?.let { ResponseCodeManager.checkRetrofitApiResponse(it) }
                            )
                        }
                        is IOException ->{
                            NetworkResult.Failure(
                                true,
                                null,
                                null,
                                Constants.STS_DEFAULT
                            )
                        }
                        else -> {
                            NetworkResult.Failure(
                                false,
                                null,
                                null,
                                Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCastData(actorId: Int): Flow<NetworkResult<Response<ActorCastResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = actorApiService.getCastData(actorId)
                emit(NetworkResult.Success(response))
            } catch (throwable: Throwable){
                emit(
                    when(throwable){
                        is HttpException ->{
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()?.let { ResponseCodeManager.checkRetrofitApiResponse(it) }
                            )
                        }
                        is IOException ->{
                            NetworkResult.Failure(
                                true,
                                null,
                                null,
                                Constants.STS_DEFAULT
                            )
                        }
                        else -> {
                            NetworkResult.Failure(
                                false,
                                null,
                                null,
                                Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun isFavoriteActor(actorId: Int) =
        databaseDataSource.checkIfActorIsFavorite(actorId)

    override suspend fun addActorsToFavorite(favoriteActor: FavoriteActor) =
        databaseDataSource.addActorToFavorites(favoriteActor)

    override suspend fun deleteSelectedFavoriteActor(favoriteActor: FavoriteActor) =
        databaseDataSource.deleteSelectedFavoriteActor(favoriteActor)

    override fun getAllFavoriteActors(): Flow<List<FavoriteActor>> =
        databaseDataSource.getAllFavoriteActors()
}
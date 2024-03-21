package com.example.actors.data.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.actors.data.datasource.network.retrofit.model.ActorItem
import com.example.actors.data.datasource.network.retrofit.service.ActorService
import java.io.IOException
import javax.inject.Inject


class TrendingActorSource @Inject constructor(
    private val apiService: ActorService,
): PagingSource<Int, ActorItem>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ActorItem> {
        val position = params.key ?: 1
        return try {
            val data = apiService.getTrendingActors(
                page = position
            )
            val nextKey = if (data.results?.isEmpty() == true){
                null
            } else{
                position + (params.loadSize / 10)
            }
            val prevKey = if (position == 1) null else position -1
            LoadResult.Page(
                data = data.results!!,
                prevKey = prevKey,
                nextKey = nextKey
            )



        } catch (e: IOException){
            return LoadResult.Error(e)
        } catch (e: HttpException){
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ActorItem>): Int? {
        return state.anchorPosition
    }
}
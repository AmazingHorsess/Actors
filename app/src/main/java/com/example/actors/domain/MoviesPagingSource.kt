package com.example.actors.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.actors.data.model.Movie
import com.example.actors.data.model.PagedResponse
import com.example.actors.domain.repository.movies.MovieRepository
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPageNumber = params.key ?: 1

            val movies: PagedResponse<Movie> = movieRepository.getNowPlayingMoviesData(
                page = currentPageNumber
            )

            val nextKey = when {
                (params.loadSize * (currentPageNumber + 1)) < movies.total -> currentPageNumber + 1
                else -> null
            }

            return LoadResult.Page(
                prevKey = null,
                nextKey = nextKey,
                data = movies.data
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
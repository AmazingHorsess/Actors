package com.example.actors.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.actors.data.model.Movie
import com.example.actors.domain.repository.movies.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedMovies @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(
        viewModelScope: CoroutineScope
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            initialKey = INITIAL_PAGE_KEY,
            pagingSourceFactory = {
                MoviesPagingSource(movieRepository)
            }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val INITIAL_PAGE_KEY = 1
        private const val PAGE_SIZE = 20
    }
}
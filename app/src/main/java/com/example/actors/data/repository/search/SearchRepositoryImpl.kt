package com.example.actors.data.repository.search

import com.example.actors.data.datasource.network.json.NetworkDataSource
import com.example.actors.data.model.Actor
import com.example.actors.data.model.Movie
import com.example.actors.domain.repository.search.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
): SearchRepository {
    override suspend fun getSearchableActorsData(query: String): List<Actor> {
        return networkDataSource.getSearchableActorsData(query)
    }

    override suspend fun getSearchableMoviesData(query: String): List<Movie> {
        return networkDataSource.getSearchableMoviesData(query)
    }

}
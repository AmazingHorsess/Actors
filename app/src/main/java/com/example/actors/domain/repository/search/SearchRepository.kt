package com.example.actors.domain.repository.search

import androidx.room.Query
import com.example.actors.data.model.Actor
import com.example.actors.data.model.Movie

interface SearchRepository {

    suspend fun getSearchableActorsData(query: String): List<Actor>

    suspend fun getSearchableMoviesData(query: String): List<Movie>


}
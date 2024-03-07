package com.example.actors.domain.repository.movies

import com.example.actors.data.model.Trailer

interface MovieTrailerRepository {

    suspend fun getMovieTrailerUrl(movieId: Int): List<Trailer>

    suspend fun getMovieTrailerThumbnail(trailerId: String)




}
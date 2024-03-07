package com.example.actors.data.datasource.network

import com.example.actors.utils.TmdbApiKey
import java.net.URL

object HttpRoutes {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "api_key=${TmdbApiKey.TMDB_API_KEY}"

    // https://api.themoviedb.org/3/person/popular?api_key=API_KEY
    const val getPopularActorsUrl ="${BASE_URL}person/popular?${API_KEY}"




}
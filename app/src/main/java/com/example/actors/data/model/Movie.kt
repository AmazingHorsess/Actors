package com.example.actors.data.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.actors.data.datasource.database.entity.FavoriteMoviesEntity


@Immutable
data class Movie(
    @Stable val movieId: Int,
    val movieName: String,
    val posterPathUrl: String,
    val bannerUrl: String
)





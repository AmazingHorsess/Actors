package com.example.actors.data.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("id") @Stable val genreId:Int,
    @SerialName("name") val genreName:String
)

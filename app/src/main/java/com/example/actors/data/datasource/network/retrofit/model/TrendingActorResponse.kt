package com.example.actors.data.datasource.network.retrofit.model

import kotlinx.serialization.SerialName

data class TrendingActorResponse (

    @SerialName("page") val page: Int? = null,

    @SerialName("totalPages") val totalPages:Int? = null,

    @SerialName("results") val results : List<ActorItem>? = null,

    @SerialName("total_results") val totalResults : Int? = null,
)


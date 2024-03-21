package com.example.actors.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class MovieProvider(
    val flatrate: ArrayList<Flatrate>,
)
@Serializable
data class Flatrate(
    @SerialName("logo_path") val logoPath: String,
    @SerialName("provider_id") val providerId: Int,
    @SerialName("provider_name") val providerName: String,

    )
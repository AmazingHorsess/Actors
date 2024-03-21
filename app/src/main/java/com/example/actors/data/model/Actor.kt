package com.example.actors.data.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Actor(

   @SerialName("id") @Stable val actorId: Int,
   @SerialName("name") val actorName: String,
   @SerialName("profile_path") val profileUrl: String
)

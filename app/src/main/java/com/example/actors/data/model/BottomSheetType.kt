package com.example.actors.data.model

enum class BottomSheetType(var movieOrActorId: Int? = null) {
    MovieDetailBottomSheet(movieOrActorId = null),
    ActorDetailBottomSheet(movieOrActorId = null)
}
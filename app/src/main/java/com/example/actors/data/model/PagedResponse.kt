package com.example.actors.data.model

data class PagedResponse<T>(
    val data: List<T>,
    val total: Int,
    val page: Int
)

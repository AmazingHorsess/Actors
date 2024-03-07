package com.example.actors.utils

object TmdbApiKey {
    const val TMDB_API_KEY = "0df77bb8ba8b74ad1334f90b0bf638d3"
}

fun isTmdbApiKeyNotValid(): Boolean {
    return TmdbApiKey.TMDB_API_KEY.isEmpty()
}
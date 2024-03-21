package com.example.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val tmdbDispatcher: TmdbDispatchers)

enum class TmdbDispatchers {
    Default,
    IO,
}
package com.example.core.network.di

import com.example.core.network.network_calls.person.TmdbPersonDataSource
import com.example.core.network.retrofit.RetrofitTmdbNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
    fun binds(impl:RetrofitTmdbNetwork): TmdbPersonDataSource
}
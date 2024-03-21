package com.example.actors.di

import com.example.actors.data.datasource.network.retrofit.service.ActorService
import com.example.actors.data.datasource.network.retrofit.util.Constants.CONNECT_TIMEOUT
import com.example.actors.data.datasource.network.retrofit.util.Constants.READ_TIMEOUT
import com.example.actors.data.datasource.network.retrofit.util.Constants.WRITE_TIMEOUT
import com.example.actors.data.datasource.network.retrofit.util.createNetworkClient
import com.example.actors.utils.TmdbApiKey
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun clientInterceptor() : Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    client.addInterceptor(httpLoggingInterceptor)
                    client.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    client.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    client.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    client.addNetworkInterceptor(clientInterceptor())
                }.build()
            )
            .addConverterFactory(Json.asConverterFactory(contentType = contentType))
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideActorService(retrofit: Retrofit): ActorService =
        retrofit.create(ActorService::class.java)
}
private const val BASE_URL = "https://api.themoviedb.org/"
private const val API_KEY = "api_key=${TmdbApiKey.TMDB_API_KEY}"
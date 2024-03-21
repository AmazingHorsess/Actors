package com.example.actors.data.datasource.network.retrofit.util


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.utils.EmptyContent.contentType
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

fun createNetworkClient(
    baseUrl:String,
    apiKey: String,
) =
    retrofitClient(baseUrl,httpClient(apiKey))

private fun  httpClient(
    apiKey:String
): OkHttpClient{
    val tmdbApiKeyInterceptor = TmdbApiKeyInterceptor(apiKey)
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.addInterceptor(tmdbApiKeyInterceptor)
    return clientBuilder.build()

}
private fun retrofitClient(
    baseUrl: String,
    httpClient: OkHttpClient,
): Retrofit {
    val json = Json
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())
    return Retrofit.Builder()
        .baseUrl(baseUrl)
       .addConverterFactory(jsonConverterFactory)
        .client(httpClient)
        .build()

}

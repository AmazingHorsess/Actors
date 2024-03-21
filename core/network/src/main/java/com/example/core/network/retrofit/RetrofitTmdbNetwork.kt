package com.example.core.network.retrofit

import com.example.core.network.model.MovieCreditResponseDTO
import com.example.core.network.model.PersonDetailDTO
import com.example.core.network.model.PopularPersonResponseDTO
import com.example.core.network.model.TrendingPersonResponseDTO
import com.example.core.network.network_calls.person.TmdbPersonDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.openjdk.tools.sjavac.Log.trace
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import androidx.tracing.trace





private interface RetrofitTmdbNetworkApi {
    @GET("3/trending/person/{time_window}")
    suspend fun getTrendingActors(
        @Path("time_window") trendingDate: String = "week",
        @Query("page") page: Int?
    ): NetworkResponse<TrendingPersonResponseDTO>

    @GET("3/person/popular")
    suspend fun getPopularActors(
        @Query("page") page: Int?
    ): NetworkResponse<PopularPersonResponseDTO>

    @GET("3/person/{personId}/movie_credits")
    suspend fun getCastData(
        @Path("personId") actorId: Int
    ): NetworkResponse<MovieCreditResponseDTO>

    @GET("3/person/{personId}")
    suspend fun getSelectedActor(
        @Path("personId") actorId: Int
    ): NetworkResponse<PersonDetailDTO>

}
@Serializable
private data class NetworkResponse<T>(
    val data: T,
)
private const val BASE_URL = "https://api.themoviedb.org/"
const val CONNECT_TIMEOUT = 20L
const val READ_TIMEOUT = 60L
const val WRITE_TIMEOUT = 120L

//TODO: HIDE API KEY
const val API_KEY = "0df77bb8ba8b74ad1334f90b0bf638d3"
private fun clientInterceptor() : Interceptor =
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
@Singleton
internal class RetrofitTmdbNetwork @Inject constructor(
    networkJson: Json,
): TmdbPersonDataSource{
    private val httpClient = createOkHttpClient()
    private val networkApi = trace("RetrofitTmdbNetwork"){
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json.".toMediaType()),
            )
            .client(httpClient)
            .build()
            .create(RetrofitTmdbNetwork::class.java)
    }
    override suspend fun getPopularPersons(): PopularPersonResponseDTO =
        networkApi.getPopularPersons()

    override suspend fun getTrendingPersons(): TrendingPersonResponseDTO =
        networkApi.getTrendingPersons()

    override suspend fun getDetailPerson(personId: Int): PersonDetailDTO =
        networkApi.getDetailPerson(personId)

    override suspend fun getCastData(personId: Int): MovieCreditResponseDTO =
        networkApi.getCastData(personId)

}
private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        addInterceptor(httpLoggingInterceptor)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        addNetworkInterceptor(clientInterceptor())
    }.build()
}
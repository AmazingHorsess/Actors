package com.example.core.network.di
import android.content.Context
import androidx.tracing.trace
import coil.ImageLoader
import com.example.core.network.fake.FakeAssetManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FakeModule {

    @Provides
    @Singleton
    fun providesDemoAssetManager(
        @ApplicationContext context: Context,
    ): FakeAssetManager = FakeAssetManager(context.assets::open)

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = trace("NiaOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()


            )
            .build()
    }

    /**
     * Since we're displaying SVGs in the app, Coil needs an ImageLoader which supports this
     * format. During Coil's initialization it will call `applicationContext.newImageLoader()` to
     * obtain an ImageLoader.
     *
     * @see <a href="https://github.com/coil-kt/coil/blob/main/coil-singleton/src/main/java/coil/Coil.kt">Coil</a>
     */
    @Provides
    @Singleton
    fun imageLoader(
        // We specifically request dagger.Lazy here, so that it's not instantiated from Dagger.
        okHttpCallFactory: dagger.Lazy<Call.Factory>,
        @ApplicationContext application: Context,
    ): ImageLoader = trace("NiaImageLoader") {
        ImageLoader.Builder(application)
            .callFactory { okHttpCallFactory.get() }
            // Assume most content images are versioned urls
            // but some problematic images are fetching each time
            .respectCacheHeaders(false)
            .build()
    }
}


private const val BASE_URL = "https://api.themoviedb.org/"
private const val API_KEY = "api_key=0df77bb8ba8b74ad1334f90b0bf638d3}"
private const val LANG = "en-US/"

private const val CONNECT_TIMEOUT = 20L
private const val READ_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 120L

private const val NETWORK_PAGE_SIZE =10
private const val STARTING_PAGE_INDEX = 1

private const val STS_401 = "Unauthorized!"
private const val STS_403 = "Forbidden!"
private const val STS_404 = "Not Found"
private const val STS_408 = "Request Timed Out"
private const val STS_500 = "Internal Server Error"
private const val STS_502 = "Bad Gateway!"
private const val STS_503 = "Service Unavailable!"
private const val STS_504 = "Gateway Timeout"

private const val STS_DEFAULT = "Something went wrong, Please try again!"
private const val NETWORK_FAILURE = "You are not connected to the internet. Make sure your network connection and try again."
private const val CONVERSION_FAILURE = "Conversion Error"


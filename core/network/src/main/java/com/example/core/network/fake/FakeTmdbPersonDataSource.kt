package com.example.core.network.fake

import JvmUnitTestFakeAssetManager
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import com.example.core.common.Dispatcher
import com.example.core.common.TmdbDispatchers
import com.example.core.network.model.MovieCreditResponseDTO
import com.example.core.network.model.PersonDetailDTO
import com.example.core.network.model.PopularPersonResponseDTO
import com.example.core.network.model.TrendingPersonResponseDTO
import com.example.core.network.network_calls.person.TmdbPersonDataSource
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class FakeTmdbPersonDataSource @Inject constructor(
    @Dispatcher(TmdbDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
    private val networkJson: Json
) : TmdbPersonDataSource {
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getPopularPersons(): PopularPersonResponseDTO = withContext(ioDispatcher){
        assets.open(POPULAR_PERSON).use(networkJson::decodeFromStream)
    }


    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTrendingPersons(): TrendingPersonResponseDTO = withContext(ioDispatcher){
        assets.open(TRENDING_PERSON).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getDetailPerson(personId: Int): PersonDetailDTO = withContext(ioDispatcher){
        assets.open(DETAIL_PERSON).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getCastData(personId: Int): MovieCreditResponseDTO = withContext(ioDispatcher){
        assets.open(MOVIE_CREDITS_PERSON).use(networkJson::decodeFromStream)

    }

    companion object {
        private const val POPULAR_PERSON = "popular_person.json"
        private const val TRENDING_PERSON = "trending_person.json"
        private const val DETAIL_PERSON = "detail_person.json"
        private const val MOVIE_CREDITS_PERSON = "movie_credits.json"
    }
}
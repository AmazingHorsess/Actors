package com.example.core.network.network_calls.person

import com.example.core.network.model.Cast
import com.example.core.network.model.MovieCreditResponseDTO
import com.example.core.network.model.PersonDetailDTO
import com.example.core.network.model.PopularPersonResponseDTO
import com.example.core.network.model.TrendingPersonResponseDTO

interface TmdbPersonDataSource {
    suspend fun getPopularPersons(): PopularPersonResponseDTO

    suspend fun getTrendingPersons(): TrendingPersonResponseDTO

    suspend fun getDetailPerson(personId:Int): PersonDetailDTO

    suspend fun getCastData(personId: Int): MovieCreditResponseDTO
}
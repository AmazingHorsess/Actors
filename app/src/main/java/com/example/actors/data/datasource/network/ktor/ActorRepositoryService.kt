package com.example.actors.data.datasource.network.ktor

import androidx.lifecycle.LiveData
import com.example.actors.data.datasource.network.HttpRoutes
import com.example.actors.data.datasource.network.RequestUrls
import com.example.actors.data.model.Actor
import com.example.actors.data.model.ActorDetail
import com.example.actors.data.model.FavoriteActor
import com.example.actors.data.model.Movie
import com.example.actors.domain.repository.actor.ActorRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*


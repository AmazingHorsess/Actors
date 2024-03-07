package com.example.actors.data.datasource.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.actors.data.datasource.database.entity.FavoriteActorsEntity

@Dao
interface FavoriteActorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addActorToFavorites(favoriteActorsEntity: FavoriteActorsEntity)

    @Query("SELECT * FROM favorite_actors_table")
    fun getAllFavoriteActors(): LiveData<List<FavoriteActorsEntity>>

    @Query("DELETE FROM favorite_actors_table")
    fun deleteAllFavoriteActors()

    @Delete
    fun deleteSelectedFavoriteActor(favoriteActorsEntity: FavoriteActorsEntity)

    @Query("SELECT column_actor_id FROM favorite_actors_table WHERE column_actor_id = :actorId")
    fun checkIfActorIsFavorite(actorId: Int): LiveData<Int>
}
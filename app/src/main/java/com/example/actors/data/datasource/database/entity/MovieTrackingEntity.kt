package com.example.actors.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_tracking_table")
data class MovieTrackingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "movie_name")
    val movieName:String,

    @ColumnInfo(name = "progress")
    val progress: Int,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "movie_id")
    val movieId:Int,

    @ColumnInfo(name = "start_date")
    val startDate: Long? = null,

    @ColumnInfo(name = "end_date")
    val endDate: Long? = null


)

sealed class MovieStatus{
    object Watching : MovieStatus()
    object Completed : MovieStatus()
    object PlanToWatch : MovieStatus()
    object OnHold : MovieStatus()
    object Dropped : MovieStatus()

}

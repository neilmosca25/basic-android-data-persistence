package com.neilmosca.basicandroiddatapersistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {

    @Insert
    suspend fun createMovie(movie: Movie): Long

    @Query("SELECT * FROM movie_table")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    suspend fun getMovie(id: Long): Movie?

    @Update
    suspend fun updateMovie(movie: Movie): Int

    @Delete
    suspend fun deleteMovie(movie: Movie): Int
}
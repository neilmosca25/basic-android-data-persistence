package com.neilmosca.basicandroiddatapersistence

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.max

interface MovieDao {
    suspend fun createMovie(movie: Movie): Boolean

    suspend fun getMovies(): List<Movie>

    suspend fun getMovie(id: Long): Movie?

    suspend fun updateMovie(movie: Movie): Boolean

    suspend fun deleteMovie(id: Long): Boolean
}

class MovieDaoImpl (private val realm: Realm) : MovieDao {
    override suspend fun createMovie(movie: Movie): Boolean {
        try {
            realm.write {
                // Query all users, find the highest id value, and default to 0 if empty
                val maxId =  query<Movie>().max<Long>("id").find() ?: 0
                val nextId = maxId + 1

                val newMovie = Movie().apply {
                    id = nextId
                    title = movie.title
                    genre = movie.genre
                    year = movie.year
                }
                copyToRealm(newMovie)
            }
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMovies(): List<Movie> {
        return realm.query(Movie::class).find()
    }

    override suspend fun getMovie(id: Long): Movie? {
        return realm.query(Movie::class, "id == $0", id).first().find()
    }

    override suspend fun updateMovie(movie: Movie): Boolean {
        try {
            realm.write {
                val movieToUpdate = Movie().apply {
                    id = movie.id
                    title = movie.title
                    genre = movie.genre
                    year = movie.year
                }
                copyToRealm(movieToUpdate, updatePolicy = UpdatePolicy.ALL)
            }
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun deleteMovie(id: Long): Boolean {
        try {
            realm.write {
                val movieToDelete = query<Movie>("id == $0", id).find()
                delete(movieToDelete)
            }
            return true
        } catch (e: Exception) {
            throw e
        }
    }
}
package com.neilmosca.basicandroiddatapersistence

class MovieRepository (private val movieDao: MovieDao) {

    suspend fun createMovie(movie: Movie): Boolean {
        val rowId = movieDao.createMovie(movie = movie)
        if (rowId != -1L) {
            return true
        }
        else {
            throw Exception("Insertion failed or was ignored due to conflict")
        }
    }

    suspend fun getMovies(): List<Movie> = movieDao.getMovies()

    suspend fun getMovie(id: Long): Movie {
        val movie = movieDao.getMovie(id = id)
        if (movie != null ) {
            return movie
        }
        else {
            throw Exception("Movie not found")
        }
    }

    suspend fun updateMovie(movie: Movie): Boolean {
        val updatedRows  = movieDao.updateMovie(movie = movie)
        if (updatedRows > 0) {
            return true
        } else {
            throw Exception("Movie not found")
        }
    }

    suspend fun deleteMovie(movie: Movie): Boolean {
        val deletedCount = movieDao.deleteMovie(movie = movie)
        if (deletedCount > 0) {
            return true
        }
        else {
            throw Exception("Movie not found")
        }
    }
}
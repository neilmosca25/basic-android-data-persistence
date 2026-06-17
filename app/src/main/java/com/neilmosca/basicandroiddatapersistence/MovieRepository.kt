package com.neilmosca.basicandroiddatapersistence

class MovieRepository (private val movieDao: MovieDao) {

    suspend fun getMovies(): List<Movie> = movieDao.getMovies()

    suspend fun getMovie(id: Long): Movie? {
        return movieDao.getMovie(id = id)
    }

    suspend fun createMovie(movie: Movie): Boolean {
        return movieDao.createMovie(movie = movie)
    }

    suspend fun updateMovie(movie: Movie): Boolean {
        return movieDao.updateMovie(movie = movie)
    }

    suspend fun deleteMovie(id: Long): Boolean {
        return movieDao.deleteMovie(id = id)
    }
}
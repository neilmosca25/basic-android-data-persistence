package com.neilmosca.basicandroiddatapersistence

sealed class MovieIntent {
    // Create
    data class CreateMovie(val movie: Movie) : MovieIntent()

    // Read
    object GetMovies : MovieIntent()
    data class GetMovie(val id: Long) : MovieIntent()

    // Update
    data class UpdateMovie(val movie: Movie) : MovieIntent()

    // Delete
    data class DeleteMovie(val movie: Movie) : MovieIntent()
}
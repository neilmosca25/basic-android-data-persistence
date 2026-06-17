package com.neilmosca.basicandroiddatapersistence

data class MovieViewState(
   val movies: List<Movie> = emptyList(),
   val isLoading: Boolean = false,
   val hasError: Boolean = false
)

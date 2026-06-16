package com.neilmosca.basicandroiddatapersistence

import java.util.Collections.emptyList

data class MovieViewState(
   val movies: MutableList<Movie> = emptyList(),
   val isLoading: Boolean = false,
   val hasError: Boolean = false
)

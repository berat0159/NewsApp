package com.courage.newsapp.presentation.search

import androidx.paging.PagingData
import com.courage.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val error: String? = null
) {
}
package com.courage.newsapp.presentation.bookmark

import com.courage.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)

package com.courage.newsapp.data.remote.dto

import com.courage.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
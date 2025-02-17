package com.courage.newsapp.domain.usecases.news

import com.courage.newsapp.data.local.NewsDao
import com.courage.newsapp.domain.model.Article
import com.courage.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}
package com.courage.newsapp.domain.usecases.news

import com.courage.newsapp.data.local.NewsDao
import com.courage.newsapp.domain.model.Article
import com.courage.newsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}
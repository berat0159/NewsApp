package com.courage.newsapp.data.remote.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.courage.newsapp.domain.model.Article
import com.courage.newsapp.utils.Constants.API_KEY

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition.let { anchorPosition ->
            val anchorPage = anchorPosition?.let { state.closestPageToPosition(it) }
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount =0


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.searchNews(searchQuery = searchQuery,page = page, sources = sources, apiKey = API_KEY)
            totalNewsCount = newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }


}
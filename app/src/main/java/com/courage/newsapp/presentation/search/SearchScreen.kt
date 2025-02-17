package com.courage.newsapp.presentation.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.courage.newsapp.domain.model.Article
import com.courage.newsapp.presentation.common.ArticlesList
import com.courage.newsapp.presentation.common.SearchBar
import com.courage.newsapp.presentation.nvgraph.Route
import com.courage.newsapp.ui.theme.NewsAppTheme

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails:(Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 24.dp, end = 24.dp)
            .statusBarsPadding()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {navigateToDetails(it)})
            Log.d("SearchScreen", "Articles state: ${state.articles}")
        }
    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    NewsAppTheme {
        val viewModel: SearchViewModel = hiltViewModel()
        SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigateToDetails = {})
    }
}
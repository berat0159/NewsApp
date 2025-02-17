package com.courage.newsapp.presentation.details.components

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.courage.newsapp.domain.model.Article
import com.courage.newsapp.domain.model.Source
import com.courage.newsapp.presentation.nvgraph.Route
import com.courage.newsapp.presentation.onboarding.Dimens.MediumPadding1
import com.courage.newsapp.ui.theme.NewsAppTheme
import com.courage.newsapp.utils.Constants.ArticleImageHeight


@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: (String) -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article = article))
            },
            onBackClick = { navigateUp.invoke(Route.AppStartNavigation.route) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(), contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview(){
    NewsAppTheme {
        DetailsScreen(
            article = Article(
                author = "John Doe",
                content = "Bu haber, örnek bir haber içeriğidir. Bu bölümde haberin detayları yer alır. Haber içeriği, kullanıcıların bilgilendirilmesi amacıyla hazırlanmıştır.",
                description = "Bu haber, örnek bir haber açıklamasıdır. Haber detayları için içeriği okuyabilirsiniz.",
                publishedAt = "2023-10-15T12:34:56Z",
                source = Source(id = "bbc-news", name = "BBC News"),
                title = "Örnek Haber Başlığı",
                url = "https://www.example.com",
                urlToImage = "https://picsum.photos/600/400" // Örnek bir resim URL'si
            ),
            event ={},
            navigateUp = {}
        )
    }
}

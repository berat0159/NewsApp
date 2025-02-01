package com.courage.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.courage.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image:Int
)


val pages = listOf(
    Page(
        title = "Explore the World",
        description = "Discover the latest news from around the world",
        image = R.drawable.start1
    ),
    Page(
        title = "Explore the World",
        description = "Discover the latest news from around the world",
        image = R.drawable.start2
    ),
    Page(
        title = "Explore the World",
        description = "Discover the latest news from around the world",
        image = R.drawable.start3
    )
)

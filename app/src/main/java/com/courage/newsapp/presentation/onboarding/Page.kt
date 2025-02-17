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
        title = "Stay Informed",
        description = "Get the latest news and updates from around the world, all in one place.",
        image = R.drawable.start1
    ),
    Page(
        title = "Personalized for You",
        description = "Follow your favorite topics and sources to see the news that matters most to you.",
        image = R.drawable.start2
    ),
    Page(
        title = "Fast & Reliable",
        description = "Access real-time news with a smooth and user-friendly experience.",
        image = R.drawable.start3
    )
)

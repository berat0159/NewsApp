package com.courage.newsapp.di

import android.app.Application
import androidx.room.Room
import com.courage.newsapp.data.local.NewsDao
import com.courage.newsapp.data.local.NewsDataBase
import com.courage.newsapp.data.local.NewsTypeConvertor
import com.courage.newsapp.data.manager.LocalUserManagerImpl
import com.courage.newsapp.data.remote.dto.NewsApi
import com.courage.newsapp.data.repository.NewsRepositoryImpl
import com.courage.newsapp.domain.manager.LocalUserManager
import com.courage.newsapp.domain.repository.NewsRepository
import com.courage.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.courage.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.courage.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.courage.newsapp.domain.usecases.news.DeleteArticle
import com.courage.newsapp.domain.usecases.news.GetNews
import com.courage.newsapp.domain.usecases.news.NewsUseCases
import com.courage.newsapp.domain.usecases.news.SearchNews
import com.courage.newsapp.domain.usecases.news.SelectArticle
import com.courage.newsapp.domain.usecases.news.SelectArticles
import com.courage.newsapp.domain.usecases.news.UpsetArticle
import com.courage.newsapp.utils.Constants.BASE_URL
import com.courage.newsapp.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModele {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager = localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager = localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ) : NewsRepository = NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) : NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsetArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )

    }


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDataBase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ):NewsDao = newsDataBase.newsDao


}
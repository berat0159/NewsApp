package com.courage.newsapp.di

import android.app.Application
import com.courage.newsapp.data.manager.LocalUserManagerImpl
import com.courage.newsapp.domain.manager.LocalUserManager
import com.courage.newsapp.domain.usecases.AppEntryUseCases
import com.courage.newsapp.domain.usecases.ReadAppEntry
import com.courage.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}
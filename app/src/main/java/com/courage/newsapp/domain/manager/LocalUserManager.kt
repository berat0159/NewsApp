package com.courage.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppEntry()

    val readAppEntry: Flow<Boolean>
}
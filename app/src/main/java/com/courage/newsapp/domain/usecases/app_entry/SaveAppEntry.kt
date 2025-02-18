package com.courage.newsapp.domain.usecases.app_entry

import com.courage.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager

) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()

    }
}
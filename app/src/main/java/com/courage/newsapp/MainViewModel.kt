package com.courage.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courage.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.courage.newsapp.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination = mutableStateOf(Route.AppStartNavigation.route)
        private set


    init {
        appEntryUseCases.readAppEntry().onEach{
            shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen){
                startDestination.value = Route.NewsNavigation.route
            }else {
                startDestination.value = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}
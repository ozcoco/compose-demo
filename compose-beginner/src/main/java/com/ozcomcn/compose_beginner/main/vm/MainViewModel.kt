package com.ozcomcn.compose_beginner.main.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.base.nav.Navigator
import com.ozcomcn.compose_beginner.data.AppSettingRepository
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    val isDarkTheme: Boolean,
)

sealed interface MainEvent {
    class NavigateBack : MainEvent
    data class NavigateTo(val destination: NavKey) : MainEvent
    data class DarkThemeChange(val isDarkTheme: Boolean) : MainEvent
}

@HiltViewModel
class MainViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    val entryProviderBuilders: Set<@JvmSuppressWildcards EntryProviderInstaller>,
    val appSettingRepository: AppSettingRepository
) : ViewModel() {

    @MainNavQualifier
    @Inject
    lateinit var navKeys: Lazy<Set<NavKey>>

    @MainNavQualifier
    @Inject
    lateinit var navigator: Navigator

    private val _uiState = MutableStateFlow(
        MainUiState(
            isDarkTheme = savedStateHandle.get<Boolean>("isDarkTheme") ?: false
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            appSettingRepository.isDarkTheme().collect { isDarkTheme ->
                _uiState.update {
                    it.copy(isDarkTheme = isDarkTheme)
                }
            }
        }
    }

    fun onEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.NavigateBack -> {
                    navigator.goBack()
                }

                is MainEvent.NavigateTo -> {
                    val navKey = event.destination as BaseNavKey
                    if (navKey.isMainNavKey) navigator.clearBackStack()
                    navigator.goTo(event.destination)
                }

                is MainEvent.DarkThemeChange -> {
                    _uiState.update {
                        it.copy(isDarkTheme = event.isDarkTheme)
                    }
                    savedStateHandle["isDarkTheme"] = event.isDarkTheme
                    appSettingRepository.setDarkTheme(event.isDarkTheme)
                }

            }
        }
    }
}
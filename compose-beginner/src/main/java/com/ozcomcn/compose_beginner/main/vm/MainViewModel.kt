package com.ozcomcn.compose_beginner.main.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.base.nav.Navigator
import com.ozcomcn.compose_beginner.base.vm.BaseViewModel
import com.ozcomcn.compose_beginner.data.AppSettingKeys
import com.ozcomcn.compose_beginner.data.AppSettingRepository
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainState(
    val isDarkTheme: Boolean,
    val isUseLocalModel: Boolean,
)

sealed interface MainIntent {
    class NavigateBack : MainIntent
    data class NavigateTo(val destination: NavKey) : MainIntent
    data class DarkThemeChange(val isDarkTheme: Boolean) : MainIntent
    data class UseLocalModel(val isUseLocalModel: Boolean) : MainIntent
}

sealed interface MainEffect {
    data class NavigateTo(val destination: NavKey) : MainEffect
    object UseLocalModelChange : MainEffect
}

@HiltViewModel
class MainViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    val entryProviderBuilders: Set<@JvmSuppressWildcards EntryProviderScope>,
    val appSettingRepository: AppSettingRepository
) : BaseViewModel<MainIntent, MainState, MainEffect>() {

    @MainNavQualifier
    @Inject
    lateinit var navKeys: Lazy<Set<NavKey>>

    @MainNavQualifier
    @Inject
    lateinit var navigator: Navigator

    init {
        viewModelScope.launch {
            appSettingRepository.isDarkTheme()
                .combine(appSettingRepository.isUseLocalModel()) { isDarkTheme, isUseLocalModel ->
                    MainState(isDarkTheme, isUseLocalModel)
                }.collect { mainState ->
                    updateState {
                        copy(
                            isDarkTheme = mainState.isDarkTheme,
                            isUseLocalModel = mainState.isUseLocalModel
                        )
                    }
                }
        }
    }

    override fun initState(): MainState = MainState(
        isDarkTheme = savedStateHandle.get<Boolean>(AppSettingKeys.IS_DARK_THEME.name) ?: false,
        isUseLocalModel = savedStateHandle.get<Boolean>(AppSettingKeys.IS_USE_LOCAL_MODEL.name)
            ?: false,
    )

    override fun onIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.NavigateBack -> {
                navigator.goBack()
            }

            is MainIntent.NavigateTo -> {
                val navKey = intent.destination as BaseNavKey
                if (navKey.isMainNavKey) navigator.clearBackStack()
                navigator.goTo(intent.destination)
            }

            is MainIntent.DarkThemeChange -> {
                updateState {
                    copy(isDarkTheme = intent.isDarkTheme)
                }
                savedStateHandle[AppSettingKeys.IS_DARK_THEME.name] = intent.isDarkTheme
                viewModelScope.launch {
                    appSettingRepository.setDarkTheme(intent.isDarkTheme)
                }
            }

            is MainIntent.UseLocalModel -> {
                updateState {
                    copy(isUseLocalModel = intent.isUseLocalModel)
                }
                savedStateHandle[AppSettingKeys.IS_USE_LOCAL_MODEL.name] = intent.isUseLocalModel
                viewModelScope.launch {
                    appSettingRepository.setUseLocalModel(intent.isUseLocalModel)
                }
                postEffect { MainEffect.UseLocalModelChange }
            }
        }
    }
}
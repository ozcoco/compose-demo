package com.ozcomcn.compose_beginner.components.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.Navigator
import com.ozcomcn.compose_beginner.base.vm.BaseViewModel
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ComponentsUiState(
    var isDark: Boolean = false,
)

sealed interface ComponentsEvent {
    data class NavigateTo(val destination: NavKey) : ComponentsEvent
}


@HiltViewModel
class ComponentsModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
//    @ComponentsNavQualifier val entryProviderBuilders: Set<@JvmSuppressWildcards EntryProviderInstaller>
) : BaseViewModel() {

    @ComponentsNavQualifier
    @Inject
    lateinit var navKeys: Lazy<Set<BaseNavKey>>

    @MainNavQualifier
    @Inject
    lateinit var navigator: Navigator

    var uiState by mutableStateOf(ComponentsUiState())
        private set

    fun onEvent(event: ComponentsEvent) {
        when (event) {
            is ComponentsEvent.NavigateTo -> {
                navigator.goTo(event.destination)
            }
        }
    }

}
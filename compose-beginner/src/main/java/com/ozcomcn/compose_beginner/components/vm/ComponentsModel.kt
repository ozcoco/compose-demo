package com.ozcomcn.compose_beginner.components.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.Navigator
import com.ozcomcn.compose_beginner.base.vm.BaseViewModel
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.data.ChatRepository
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Resource
import com.ozcomcn.compose_beginner.di.qualifier.DebugUserQualifier
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ComponentsUiState(
    var isDark: Boolean = false,
    var answer: String = ""
)

sealed interface ComponentsEvent {
    data class NavigateTo(val destination: NavKey) : ComponentsEvent
    data class SendMsg(val query: String) : ComponentsEvent
}


@HiltViewModel
class ComponentsModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    @ComponentsNavQualifier
    @Inject
    lateinit var navKeys: Lazy<Set<BaseNavKey>>

    @MainNavQualifier
    @Inject
    lateinit var navigator: Navigator

    @DebugUserQualifier
    @Inject
    lateinit var user: String

    var uiState by mutableStateOf(ComponentsUiState())
        private set

    private var chatJob: Job? = null
    fun sendMsg(query: String) {
        if (query.isBlank()) return
        chatJob?.cancel()
        chatJob = viewModelScope.launch {
            val query = ChatQuery(
                user = user,
                query = query
            )
            chatRepository.chatMessage(query)
                .map {
                    when (it) {
                        is Resource.Loading -> "Loading..."
                        is Resource.Success -> it.data.answer
                        is Resource.Error -> it.msg
                    }
                }.collect {
                    uiState = uiState.copy(answer = it)
                }

        }
    }

    fun onEvent(event: ComponentsEvent) {
        when (event) {
            is ComponentsEvent.NavigateTo -> {
                navigator.goTo(event.destination)
            }

            is ComponentsEvent.SendMsg -> {
                sendMsg(event.query)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        chatJob?.cancel()
    }

}
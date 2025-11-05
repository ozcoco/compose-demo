package com.ozcomcn.compose_beginner.components.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.Navigator
import com.ozcomcn.compose_beginner.base.vm.BaseViewModel
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.data.ChatRepository
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Conversations
import com.ozcomcn.compose_beginner.data.model.Messages
import com.ozcomcn.compose_beginner.data.model.Resource
import com.ozcomcn.compose_beginner.di.qualifier.DebugUserQualifier
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ComponentsState(
    var isDark: Boolean = false,
    var answer: String = "",
    var conversationId: String = "",
    var conversations: Conversations = Conversations(emptyList(), false, 0),
    var messages: Messages = Messages(emptyList(), false, 0),
)

sealed interface ComponentsIntent {
    data class NavigateTo(val destination: NavKey) : ComponentsIntent
    data class SendMsg(val query: String) : ComponentsIntent
    data class GetConversations(val user: String = "") : ComponentsIntent
    data class GetMessages(val conversationId: String) : ComponentsIntent
}

sealed interface ComponentsEffect {
    data class NavigateTo(val destination: NavKey) : ComponentsEffect
}

@HiltViewModel
class ComponentsModel @Inject constructor(
    @DebugUserQualifier private val user: String,
    private val chatRepository: ChatRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<ComponentsIntent, ComponentsState, ComponentsEffect>() {

    @ComponentsNavQualifier
    @Inject
    lateinit var navKeys: Lazy<Set<BaseNavKey>>

    @MainNavQualifier
    @Inject
    lateinit var navigator: Navigator

    // 聊天任务
    private var chatJob: Job? = null

    // 会话任务
    private var conversationsJob: Job? = null

    // 消息任务
    private var messagesJob: Job? = null


    init {
//        Log.d("ComponentsModel", "--->init: user=$user")
        // 获取用户会话列表
//        getConversations()
    }

    override fun initState(): ComponentsState = ComponentsState()

    override fun onCleared() {
        super.onCleared()
        conversationsJob?.cancel()
        messagesJob?.cancel()
        chatJob?.cancel()
    }

    override fun onIntent(intent: ComponentsIntent) {
        when (intent) {
            is ComponentsIntent.NavigateTo -> {
                // 执行页面导航操作
                navigator.goTo(intent.destination)
            }

            is ComponentsIntent.SendMsg -> {
                // 发送消息操作
                sendMsg(intent.query)
            }

            is ComponentsIntent.GetConversations -> {
                // 获取会话列表操作
                getConversations()
            }

            is ComponentsIntent.GetMessages -> {
                // 获取消息列表操作
                getMessages(intent.conversationId)
            }
        }
    }


    /**
     * 发送聊天消息并处理响应结果
     * @param query 用户输入的查询字符串
     */
    fun sendMsg(query: String) {
        // 如果查询字符串为空白则直接返回
        if (query.isBlank()) return

        // 取消之前的聊天任务
        chatJob?.cancel()

        // 启动新的聊天任务
        chatJob = viewModelScope.launch {
            val query = ChatQuery(
                user = user,
                query = query,
                conversation_id = state.value.conversationId
            )

            // 发送聊天消息并处理不同状态的响应
            chatRepository.chatMessage(query).map {
                when (it) {
                    is Resource.Loading -> "Loading..."
                    is Resource.Success -> it.data.answer
                    is Resource.Error -> it.msg
                }
            }.collect {
                // 更新UI状态中的回答内容
                updateState {
                    copy(answer = it)
                }
                // 获取该会话的消息列表
                getMessages(state.value.conversationId)
            }

        }
    }

    /**
     * 获取用户会话列表并更新UI状态
     */
    fun getConversations() {
        Log.d("ComponentsModel", "--->getConversations")
        // 取消之前的会话任务
        conversationsJob?.cancel()
        // 启动新的会话任务
        conversationsJob = viewModelScope.launch {
            // 获取用户会话列表并处理不同状态的响应
            chatRepository.conversations(user).map {
                when (it) {
                    is Resource.Loading -> "Loading..."
                    is Resource.Success -> it.data
                    is Resource.Error -> it.msg
                }
            }.collect {
                // 更新UI状态中的会话列表
                when {
                    it is Conversations -> {
                        if (it.data.isNotEmpty()) {
                            // 获取第一个会话的ID
                            val conversationId = it.data[0].id
                            // 更新UI状态中的当前会话ID和会话列表
                            updateState {
                                copy(
                                    conversationId = conversationId,
                                    conversations = it
                                )
                            }
                            // 获取该会话的消息列表
                            getMessages(conversationId)
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取会话消息列表并更新UI状态
     * @param conversationId 会话ID
     */
    fun getMessages(conversationId: String) {
        Log.d("ComponentsModel", "--->getMessages: conversationId=$conversationId")
        // 取消之前的消息任务
        messagesJob?.cancel()
        // 启动新的消息任务
        messagesJob = viewModelScope.launch {
            // 获取会话消息列表并处理不同状态的响应
            chatRepository.messages(
                user = user,
                conversation_id = conversationId,
                limit = 100,
            ).map {
                when (it) {
                    is Resource.Loading -> "Loading..."
                    is Resource.Success -> it.data
                    is Resource.Error -> it.msg
                }
            }.collect {
                // 更新UI状态中的消息列表
                when {
                    it is Messages -> {
                        updateState {
                            copy(
                                messages = it.copy(data = it.data.reversed())
                            )
                        }
                    }

                    else -> {
                        Log.d("ComponentsModel", "--->getMessages: else=$it")
                    }
                }
            }
        }
    }

}
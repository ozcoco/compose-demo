package com.ozcomcn.compose_beginner.ai.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ozcomcn.compose_beginner.base.vm.BaseViewModel
import com.ozcomcn.compose_beginner.data.ChatRepository
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Conversations
import com.ozcomcn.compose_beginner.data.model.Messages
import com.ozcomcn.compose_beginner.data.model.Resource
import com.ozcomcn.compose_beginner.di.qualifier.DebugUserQualifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AiState(
    var user: String = "",
    var conversationId: String = "",
    var conversations: Conversations = Conversations(emptyList(), false, 0),
    var messages: Messages = Messages(emptyList(), false, 0),
)

sealed interface AiIntent {
    data class UploadFiles(val files: List<String>) : AiIntent
    data class SendMsg(val query: String) : AiIntent
    data class GetChat(val conversationId: String) : AiIntent
}

sealed interface AiEffect {
    data class ShowError(val msg: String) : AiEffect
}

@HiltViewModel
class AIViewModel @Inject constructor(
    @DebugUserQualifier private val user: String,
    private val chatRepository: ChatRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<AiIntent, AiState, AiEffect>() {

    private var conversationsJob: Job? = null
    private var messagesJob: Job? = null
    private var chatJob: Job? = null

    override fun initState(): AiState = AiState(user = user)

    override fun onIntent(intent: AiIntent) {
        when (intent) {
            is AiIntent.UploadFiles -> {
                // 上传文件操作
            }

            is AiIntent.SendMsg -> {
                // 发送消息操作
                sendMsg(intent.query)
            }

            is AiIntent.GetChat -> {
                // 切换到指定会话操作
                updateState { copy(conversationId = intent.conversationId) }
                getMessages(intent.conversationId)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        chatJob?.cancel()
        messagesJob?.cancel()
        conversationsJob?.cancel()
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
                conversation_id = state.value.conversationId,
            )

            // 发送聊天消息并处理不同状态的响应
            chatRepository.chatMessage(query).map {
                when (it) {
                    is Resource.Loading -> "Loading..."
                    is Resource.Success -> it.data.answer
                    is Resource.Error -> it.msg
                }
            }.collect {
                // 获取该会话的消息列表
                getMessages(state.value.conversationId)
            }

        }
    }

    /**
     * 获取用户会话列表并更新UI状态
     */
    fun getConversations() {
        Log.d("AIViewModel", "--->getConversations")
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
                            // 更新UI状态中的当前会话ID为第一个会话ID
                            updateState {
                                copy(
                                    conversationId = it.data.first().id,
                                    conversations = it
                                )
                            }
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
        Log.d("AIViewModel", "--->getMessages: conversationId=$conversationId")
        // 取消之前的消息任务
        messagesJob?.cancel()
        // 启动新的消息任务
        messagesJob = viewModelScope.launch {
            // 获取会话消息列表并处理不同状态的响应
            chatRepository.messages(
                user = state.value.user,
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
                        Log.d("AIViewModel", "--->getMessages: else=$it")
                    }
                }
            }
        }
    }

}
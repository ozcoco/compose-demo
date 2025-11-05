package com.ozcomcn.compose_beginner.ai


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.AutoAwesomeMosaic
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.AutoAwesomeMosaic
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ozcomcn.compose_beginner.ai.di.module.AIChat
import com.ozcomcn.compose_beginner.ai.vm.AIViewModel
import com.ozcomcn.compose_beginner.ai.vm.AiEffect
import com.ozcomcn.compose_beginner.ai.vm.AiIntent
import com.ozcomcn.compose_beginner.data.model.Conversations
import com.ozcomcn.compose_beginner.main.vm.MainIntent
import com.ozcomcn.compose_beginner.main.vm.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiScreen(
    modifier: Modifier = Modifier,
    vm: AIViewModel = hiltViewModel(),
    mainVM: MainViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val onIntent: (AiIntent) -> Unit = { intent -> vm.onIntent(intent) }
    val onMainIntent: (MainIntent) -> Unit = { intent -> mainVM.onIntent(intent) }
    val state by vm.state.collectAsStateWithLifecycle()
    val nav = remember(vm) {
        listOf(
            Triple(
                Icons.AutoMirrored.Default.Chat, Icons.AutoMirrored.Outlined.Chat, "对话"
            ),
            Triple(
                Icons.Default.AutoAwesomeMosaic, Icons.Outlined.AutoAwesomeMosaic, "智能体"
            ),
            Triple(
                Icons.Default.Create, Icons.Outlined.Create, "创建"
            ),
        )
    }
    val navContent = remember(vm) {
        listOf(
            @Composable {
                AiChatContent(
                    conversations = state.conversations,
                    onEvent = onIntent,
                    onMainEvent = onMainIntent,
                    modifier = Modifier.fillMaxSize()
                )
            },
            @Composable {
                AiSmartFeatureContent(
                    modifier = Modifier.fillMaxSize()
                )
            },
            @Composable {
                AiCreateContent(
                    modifier = Modifier.fillMaxSize()
                )
            },
        )
    }
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { nav.size }
    LaunchedEffect(vm) {
        // 获取会话列表
        onIntent(AiIntent.GetConversations)
        Log.d("AiScreen", "--->LaunchedEffect: 获取会话列表")
    }
    LaunchedEffect(vm) {
        vm.effect.collect { effect ->
            when (effect) {
                is AiEffect.ShowError -> {
                    // 显示错误信息
                }
            }
        }
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            navContent[it]()
        }
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            nav.forEachIndexed { index, it ->
                val selected = selectedIndex == index
                NavigationBarItem(
                    icon = {
                        Icon(
                            if (selected) it.first else it.second,
                            contentDescription = it.third,
                        )
                    },
                    label = { Text(it.third) },
                    selected = selected,
                    onClick = {
                        selectedIndex = index
                        scope.launch {
                            pagerState.animateScrollToPage(page = index)
                        }
                    },
                )
            }
        }
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                selectedIndex = page
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiChatContent(
    conversations: Conversations,
    onEvent: (AiIntent) -> Unit = {},
    onMainEvent: (MainIntent) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(conversations.data) { item ->
            ConversationItem(
                conversation = item,
                onEvent = onEvent,
                onMainEvent = onMainEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
fun ConversationItem(
    conversation: Conversations.Data,
    onEvent: (AiIntent) -> Unit = {},
    onMainEvent: (MainIntent) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                onEvent(AiIntent.GetChat(conversation.id))
                onMainEvent(MainIntent.NavigateTo(AIChat))
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = conversation.name, modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiSmartFeatureContent(
    modifier: Modifier = Modifier,
) {
    Text("SmartFeature")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiCreateContent(
    modifier: Modifier = Modifier,
) {
    Text("Create")
}

package com.ozcomcn.compose_beginner.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ozcomcn.compose_beginner.R
import com.ozcomcn.compose_beginner.components.vm.ComponentsIntent
import com.ozcomcn.compose_beginner.components.vm.ComponentsModel
import com.ozcomcn.compose_beginner.data.model.Message

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TextFieldsScreen(
    vm: ComponentsModel = hiltViewModel()
) {
    val onIntent = { intent: ComponentsIntent ->
        vm.onIntent(intent)
    }
    val state by vm.state.collectAsStateWithLifecycle()
    val msgList = state.messages.data
    LaunchedEffect(vm) {
        // 获取会话列表
        onIntent(ComponentsIntent.GetConversations())
        Log.d("TextFieldsScreen", "--->LaunchedEffect: 获取会话列表")
    }
    Column {
        LazyColumn(
            reverseLayout = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(msgList) {
                LeftChatItem(Message(text = AnnotatedString(it.answer)))
                RightChatItem(Message(text = AnnotatedString(it.query)))
            }
        }
        InputContent(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onIntent = onIntent
        )
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LeftChatItem(message: Message = Message(text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"))) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 16.dp, bottom = 16.dp, start = 16.dp, end = 40.dp
            ),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp, 40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_header_robot),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    RoundedCornerShape(
                        topStart = 45.dp, topEnd = 45.dp, bottomStart = 0.dp, bottomEnd = 45.dp
                    )
                )
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(
                    top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp
                )
        ) {
            Text(message.text.toString())
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RightChatItem(message: Message = Message(text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"))) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 16.dp, bottom = 16.dp, start = 80.dp, end = 16.dp
            ),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .clip(
                    RoundedCornerShape(
                        topStart = 45.dp, topEnd = 0.dp, bottomStart = 45.dp, bottomEnd = 45.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(
                    top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp
                )
        ) {
            Text(message.text.toString())
        }
//        Column(
//            modifier = Modifier
//                .wrapContentSize(),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                modifier = Modifier
//                    .size(40.dp, 40.dp)
//                    .clip(CircleShape),
//                contentScale = ContentScale.Crop,
//                painter = painterResource(id = R.drawable.ic_header_robot2),
//                contentDescription = null
//            )
//        }
    }
}


@Preview
@Composable
fun InputContent(
    modifier: Modifier = Modifier, onIntent: (ComponentsIntent) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isRecording by remember { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }
    var isLongPressing by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        readOnly = isRecording,
        leadingIcon = {
            IconButton(onClick = { isRecording = !isRecording }) {
                Icon(
                    imageVector = if (isRecording) Icons.Outlined.Keyboard else Icons.Outlined.Mic,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            if (hasFocus) {
                IconButton(onClick = {
                    // 取消焦点
                    focusManager.clearFocus()
                    // 不显示光标
                    hasFocus = false
                    // 发送消息
                    onIntent(ComponentsIntent.SendMsg(text))
                    // 清空输入框
                    text = ""
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send, contentDescription = null
                    )
                }
            } else {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy((-8).dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.PhotoCamera, contentDescription = null
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.AddCircleOutline, contentDescription = null
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Start),
        shape = RoundedCornerShape(24.dp),
        value = text,
        onValueChange = { text = it },
        label = { Text(if (isRecording) "按住说话" else "发消息或者按住说话") },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .combinedClickable(onClick = {}, onLongClick = { isRecording = true })
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (!isLongPressing && event.changes.any { it.pressed }) {
                            isLongPressing = true
                            Log.d("InputContent", "--->InputContent: 按下")
                        } else {
                            isLongPressing = false
                            Log.d("InputContent", "--->InputContent: 松开")
                        }
                    }
                }
            }
            .onFocusChanged { hasFocus = it.isFocused }

    )
}
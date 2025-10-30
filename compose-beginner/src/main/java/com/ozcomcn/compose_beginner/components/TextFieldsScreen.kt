package com.ozcomcn.compose_beginner.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ozcomcn.compose_beginner.R
import com.ozcomcn.compose_beginner.components.vm.ComponentsEvent
import com.ozcomcn.compose_beginner.components.vm.ComponentsModel
import com.ozcomcn.compose_beginner.data.model.Message

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TextFieldsScreen(
    vm: ComponentsModel = hiltViewModel()
) {
    val onEvent = { event: ComponentsEvent ->
        vm.onEvent(event)
    }
    val uiState = vm.uiState
    val msgList = remember {
        listOf(
            Message(
                isUser = true,
                text = AnnotatedString("你好！今天天气不错呢～")
            ),
            Message(
                text = AnnotatedString("是的呢，适合出去走走。你有什么计划吗？")
            ),
            Message(
                isUser = true,
                text = AnnotatedString("我想去公园散步，顺便拍些照片。\n你推荐哪个角度拍照比较好？")
            ),
            Message(
                text = AnnotatedString("公园的湖边风景很美，特别是傍晚时分，光线柔和，非常适合拍照。")
            ),
            Message(
                isUser = true,
                text = AnnotatedString("听起来很棒！那我准备一下，傍晚的时候去湖边拍照。")
            ),
            Message(
                text = AnnotatedString("记得带上相机和三脚架哦，这样能拍出更稳定清晰的照片。")
            ),
            Message(
                isUser = true,
                text = AnnotatedString("好的，谢谢提醒！我已经准备好设备了。\n拍完照我们可以一起看看照片吗？")
            ),
            Message(
                text = AnnotatedString("当然可以啦！我很期待看到你拍的作品呢。")
            ),
            Message(
                isUser = true,
                text = AnnotatedString("太好了，那我们约好明天见面分享照片吧！")
            ),
        )
    }
    var text by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(msgList) {
            if (it.isUser) {
                RightChatItem(it)
            } else {
                LeftChatItem(it)
            }
        }
        item {
            Text(
                uiState.answer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        item {
            TextField(
                suffix = { Text("${text.length}/100") },
                value = text,
                onValueChange = { text = it },
                label = { Text("请输入内容") },
                placeholder = { Text("例如：Hello") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        item {
            Button(
                onClick = {
                    onEvent(ComponentsEvent.SendMsg(text))
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("发送")
            }
        }
        item {
            InputContent()
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
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
                top = 16.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 80.dp
            ),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
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
                        topStart = 45.dp,
                        topEnd = 45.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 45.dp
                    )
                )
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 16.dp,
                    end = 16.dp
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
                top = 16.dp,
                bottom = 16.dp,
                start = 80.dp,
                end = 16.dp
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
                        topStart = 45.dp,
                        topEnd = 45.dp,
                        bottomStart = 45.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            Text(message.text.toString())
        }
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp, 40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_header_robot2),
                contentDescription = null
            )
        }
    }
}


@Preview
@Composable
fun InputContent() {
    var text by remember { mutableStateOf("") }
    var isRecording by remember { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }
    var isLongPressing by remember { mutableStateOf(false) }
    OutlinedTextField(
        readOnly = isRecording,
        leadingIcon = {
            IconButton(onClick = { isRecording = !isRecording }) {
                Icon(
                    imageVector = if (isRecording) Icons.Outlined.Mic else Icons.Outlined.Keyboard,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            if (hasFocus) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send,
                        contentDescription = null
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
                            imageVector = Icons.Outlined.PhotoCamera,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.AddCircleOutline,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
        shape = RoundedCornerShape(24.dp),
        value = text,
        onValueChange = { text = it },
        label = { Text(if (isRecording) "按住说话" else "发消息或者按住说话") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .onFocusChanged { hasFocus = it.isFocused }
            .combinedClickable(
                onClick = {},
                onLongClick = { isRecording = true })
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

    )
}
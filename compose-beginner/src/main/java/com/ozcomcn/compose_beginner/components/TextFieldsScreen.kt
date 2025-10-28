package com.ozcomcn.compose_beginner.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
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
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
            Message(
                text = AnnotatedString("Helloxxx\nxxxxxxx\nxxxxxxxxxxxxx\nxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            ),
        )
    }
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        msgList.forEach {
            LeftChatItem(it)
        }
        Text(
            uiState.answer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(6f)
                .verticalScroll(rememberScrollState())
        )
        TextField(
            suffix = { Text("${text.length}/100") },
            value = text,
            onValueChange = { text = it },
            label = { Text("请输入内容") },
            placeholder = { Text("例如：Hello") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        )
        Button(
            onClick = {
                onEvent(ComponentsEvent.SendMsg(text))
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            Text("发送")
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
            .padding(16.dp),
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
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
        ) {
            Text(message.text.toString())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RightChatItem(message: Message) {
    Text(message.text)
}




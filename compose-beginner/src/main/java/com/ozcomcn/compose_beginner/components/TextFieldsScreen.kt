package com.ozcomcn.compose_beginner.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ozcomcn.compose_beginner.components.vm.ComponentsEvent
import com.ozcomcn.compose_beginner.components.vm.ComponentsModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TextFieldsScreen(
    vm: ComponentsModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val onEvent = { event: ComponentsEvent ->
        vm.onEvent(event)
    }
    val uiState = vm.uiState
    var text by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            uiState.answer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(4f)
        )
        TextField(
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
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            Text("发送")
        }
    }
}
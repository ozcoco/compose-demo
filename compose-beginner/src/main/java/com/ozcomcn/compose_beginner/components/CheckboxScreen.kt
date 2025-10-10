package com.ozcomcn.compose_beginner.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ozcomcn.compose_beginner.main.vm.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        content = {
            Text(text = "Checkbox")
        }
    )
}
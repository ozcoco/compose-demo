package com.ozcomcn.compose_beginner.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

enum class SimpleButtonsContent(
    val title: String, val content: @Composable () -> Unit
) {
    Filled(
        title = "Filled",
        content = { FilledButtonsContent() }),
    Tonal(
        title = "Tonal",
        content = { TonalButtonsContent() }),
    Outlined(
        title = "Outlined",
        content = { OutlinedButtonsContent() }),
    Elevated(
        title = "Elevated",
        content = { ElevatedButtonsContent() }),
    TextButton(
        title = "TextButton", 
        content = { TextButtonContent() }),
    FAB(
        title = "FAB",
        content = { FABContent() }),
    SmallFAB(
        title = "SmallFAB",
        content = { SmallFABContent() }),
    LargeFAB(
        title = "LargeFAB",
        content = { LargeFABContent() }),
    ExtendedFAB(
        title = "ExtendedFAB",
        content = { ExtendedFABContent() }),
}


@Composable
fun FilledButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { }) {
            Text(text = "Filled")
        }
    }
}

@Composable
fun TonalButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        FilledTonalButton(
            modifier = Modifier.align(Alignment.Center),
            onClick = { }) {
            Text(text = "Tonal")
        }
    }
}

@Composable
fun OutlinedButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        OutlinedButton(
            modifier = Modifier.align(Alignment.Center),
            onClick = { }) {
            Text(text = "Outlined")
        }
    }
}

@Composable
fun ElevatedButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        ElevatedButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { }) {
            Text(text = "Elevated")
        }
    }
}

@Composable
fun TextButtonContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        TextButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { }) {
            Text(text = "Text Button")
        }
    }
}

@Composable
fun FABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { }) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}

@Composable
fun SmallFABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        SmallFloatingActionButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { }) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }
    }
}

@Composable
fun LargeFABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        LargeFloatingActionButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { }) {
            Icon(Icons.Filled.Add, "Large floating action button")
        }
    }
}

@Composable
fun ExtendedFABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        ExtendedFloatingActionButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { },
            icon = { Icon(Icons.Filled.Add, "Extended floating action button") },
            text = { Text(text = "Extended FAB") })
    }
}
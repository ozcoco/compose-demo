package com.ozcomcn.compose_beginner.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsScreen(
    modifier: Modifier = Modifier,
) {
    Box {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            // 头
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            // 普通按钮
            item {
                Surface(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    OutlinedCard {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {}
                            ) {
                                Text(text = "Filled")
                            }
                            FilledTonalButton(onClick = {}) {
                                Text("Tonal")
                            }
                            OutlinedButton(onClick = {}) {
                                Text("Outlined")
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ElevatedButton(onClick = {}) {
                                Text("Elevated")
                            }
                            TextButton(onClick = {}) {
                                Text("Text Button")
                            }
                        }
                    }
                }
            }
            // 悬浮操作按钮
            item {
                Surface(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    OutlinedCard(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FloatingActionButton(onClick = {}) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            }

                            SmallFloatingActionButton(onClick = {}) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            }
                            LargeFloatingActionButton(
                                onClick = {},
                                shape = CircleShape
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ExtendedFloatingActionButton(
                                onClick = {},
                                icon = {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                },
                                text = {
                                    Text("Extended FAB")
                                }
                            )
                        }
                    }
                }
            }
            // 尾
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
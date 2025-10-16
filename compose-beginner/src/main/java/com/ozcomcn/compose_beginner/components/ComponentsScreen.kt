package com.ozcomcn.compose_beginner.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.components.vm.ComponentsEvent
import com.ozcomcn.compose_beginner.components.vm.ComponentsModel


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsScreen(
    modifier: Modifier = Modifier,
    vm: ComponentsModel = hiltViewModel()
) {
    val navKeys = vm.navKeys.get()
    Box(
        modifier = modifier.fillMaxSize(),
        content = {
            LazyVerticalGrid(
                modifier = Modifier.padding(16.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(navKeys.size) { index ->
                    ComponentItem(itemData = navKeys.elementAt(index)) {
                        vm.onEvent(ComponentsEvent.NavigateTo(it))
                    }
                }
            }
        }
    )
}


@Composable
fun ComponentItem(
    itemData: BaseNavKey,
    onClick: (BaseNavKey) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        onClick = { onClick(itemData) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            content = {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                    model = itemData.imgUrl,
                    contentDescription = itemData.desc
                )
                Text(
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center,
                    text = itemData.title
                )
            }
        )
    }
}

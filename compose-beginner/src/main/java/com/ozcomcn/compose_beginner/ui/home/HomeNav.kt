package com.ozcomcn.compose_beginner.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.ozcomcn.compose_beginner.ui.home.components.AppBarsScreen
import com.ozcomcn.compose_beginner.ui.home.components.ButtonsScreen
import com.ozcomcn.compose_beginner.ui.home.components.CardsScreen
import com.ozcomcn.compose_beginner.ui.home.components.CarouselScreen
import com.ozcomcn.compose_beginner.ui.home.components.CheckboxScreen
import com.ozcomcn.compose_beginner.ui.home.components.ComponentListScreen
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data object ComponentList : Screen // 首页 组件列表

    @Serializable
    data object AppBars : Screen // AppBar

    @Serializable
    data object Buttons : Screen // 按钮

    @Serializable
    data object Cards : Screen // 卡片

    @Serializable
    data object Carousel : Screen // 轮播图

    @Serializable
    data object Checkbox : Screen // 复选框
}


// 首页导航
@Preview(showBackground = true)
@Composable
fun HomeNav(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    val backStack = homeViewModel.navigator.backStack
    NavDisplay(
        modifier = modifier.padding(),
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
//            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.ComponentList> {
                ComponentListScreen()
            }
            entry<Screen.AppBars> {
                AppBarsScreen()
            }
            entry<Screen.Buttons> {
                ButtonsScreen()
            }
            entry<Screen.Cards> {
                CardsScreen()
            }
            entry<Screen.Carousel> {
                CarouselScreen()
            }
            entry<Screen.Checkbox> {
                CheckboxScreen()
            }
        })
}
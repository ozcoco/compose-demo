package com.ozcomcn.compose_beginner.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
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

    @Serializable
    data object Chips : Screen //提示汽包

    @Serializable
    data object DateTimePicker : Screen //日期时间选择器

    @Serializable
    data object Dialogs : Screen //对话框

    @Serializable
    data object Divider : Screen //分隔线

    @Serializable
    data object Lists : Screen //列表

    @Serializable
    data object LoadingProgress : Screen //加载进度条

    @Serializable
    data object Menus : Screen //菜单

    @Serializable
    data object Sheets : Screen //导航
}


// 首页导航
@Preview(showBackground = true)
@Composable
fun HomeNav(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
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
            entry<Screen.Chips> {
                CheckboxScreen()
            }
            entry<Screen.DateTimePicker> {
                CheckboxScreen()
            }
            entry<Screen.Dialogs> {
                CheckboxScreen()
            }
            entry<Screen.Divider> {
                CheckboxScreen()
            }
            entry<Screen.Lists> {
                CheckboxScreen()
            }
            entry<Screen.LoadingProgress> {
                CheckboxScreen()
            }
            entry<Screen.Menus> {
                CheckboxScreen()
            }
            entry<Screen.Sheets> {
                CheckboxScreen()
            }
        })
}
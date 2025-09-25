package com.ozcomcn.compose_beginner.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ozcomcn.compose_beginner.nav.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ComponentItemData(
    val navKey: Screen,
    val title: String,
    val desc: String,
    val imgUrl: String,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val navigator: Navigator
) : ViewModel() {

    // 列表数据
    val componentItemDataList by lazy {
        mutableStateListOf(
            ComponentItemData(
                Screen.AppBars,
                "App bars",
                "App bars are placed at the top of the screen to help people navigate through a product",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmaaqdf2b-01.png?alt=media&token=84877227-b485-453e-9c03-2cfa90804f16"
            ),
            ComponentItemData(
                Screen.Buttons,
                "Buttons",
                "When choosing the right button for an action, consider the level of emphasis each button type provides",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4kdl8gi-1.png?alt=media&token=78222465-efb0-405d-9e25-3a771b475e3a"
            ),
            ComponentItemData(
                Screen.Cards,
                "Cards",
                "Cards display content and actions about a single subject",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuilyza-1.png?alt=media&token=f4bfb5e7-e96f-41b7-8c6e-e00ab2260001"
            ),
            ComponentItemData(
                Screen.Carousel,
                "Carousel",
                "Carousels show a collection of items that can be scrolled on and off the screen",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwur4ddz-3.png?alt=media&token=a42f76c5-c4fd-46fd-89a1-04b492638986"
            ),
            ComponentItemData(
                Screen.Checkbox,
                "Checkbox",
                "Checkboxes let users select one or more items from a list, or turn an item on or off",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmabau8sl-1.png?alt=media&token=e7a6af49-16e0-4d76-9931-131edb8e672f"
            ),
            ComponentItemData(
                Screen.Checkbox,
                "Checkbox",
                "Checkboxes let users select one or more items from a list, or turn an item on or off",
                "https://images.unsplash.com/photo-1604156788856-2ce5f2171cce?fm=jpg&q=60&w=1920"
            ),
        )
    }

}
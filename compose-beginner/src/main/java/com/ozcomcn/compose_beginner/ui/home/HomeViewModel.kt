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
                Screen.Chips,
                "Chips",
                "Chips help people enter information, make selections, filter content, or trigger actions",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzthj7vk-1.png?alt=media&token=87bf4249-1c98-406e-bf83-32e0e1b6d5a6"
            ),
            ComponentItemData(
                Screen.DateTimePicker,
                "DateTimePicker",
                "Time pickers help users select and set a specific time",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flw8w5mgq-1.png?alt=media&token=291c08e3-8321-4f33-a4fc-a6b8703fb100"
            ),
            ComponentItemData(
                Screen.Dialogs,
                "Dialogs",
                "Dialogs provide important prompts in a user flow",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8qzxs3f-01.png?alt=media&token=6abae319-6bab-4fa0-8612-8e4f4e0246c7"
            ),
            ComponentItemData(
                Screen.Divider,
                "Divider",
                "Dividers are thin lines that group content in lists or other containers",
                "https://lh3.googleusercontent.com/nt6TfZJ1hPz6HX6Pn1Lvf2_jMgg3cnIGfttwCupUGgry6V5DHR1JvdSdkkWIUdVopwjJ_BRzYTaANVIo4AczkVo0jKYD3B1J8NShIWhVlYY=s0"
            ),
            ComponentItemData(
                Screen.Lists,
                "Lists",
                "Lists are continuous, vertical indexes of text and images",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwoqolbn-1.png?alt=media&token=046fb47b-0212-43c1-85d1-e321c8b98351"
            ),
            ComponentItemData(
                Screen.LoadingProgress,
                "LoadingProgress",
                "Loading indicators show the progress for a short wait time Resources",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0c2einw-03.png?alt=media&token=ee7d6244-1b86-4896-85b7-476ce06b10aa"
            ),
            ComponentItemData(
                Screen.Menus,
                "Menus",
                "Menus display a list of choices on a temporary surface",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flvozu1t4-1.png?alt=media&token=19503103-c4d8-4ce7-bb7d-426feb8a84a3"
            ),
            ComponentItemData(
                Screen.Sheets,
                "Sheets",
                "Bottom sheets show secondary content anchored to the bottom of the screen",
                "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flvp3m82k-1.png?alt=media&token=57a51bb9-a0b7-40f2-b057-0afe7d122a59"
            ),


            )
    }

}
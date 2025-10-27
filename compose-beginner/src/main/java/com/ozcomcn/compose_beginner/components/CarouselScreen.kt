package com.ozcomcn.compose_beginner.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselScreen(
    modifier: Modifier = Modifier,
) {
    val constraintSet = ConstraintSet {
        val img_bg = createRefFor("img_bg")
        val crl_big = createRefFor("crl_big")
        val crl_header = createRefFor("crl_header")
        constrain(img_bg) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(crl_big) {
            top.linkTo(img_bg.top)
            bottom.linkTo(img_bg.bottom)
            start.linkTo(img_bg.start)
            end.linkTo(img_bg.end)
        }
        constrain(crl_header) {
            bottom.linkTo(img_bg.bottom, margin = 16.dp)
            start.linkTo(img_bg.start)
            end.linkTo(img_bg.end)
        }
    }

    ConstraintLayout(
        constraintSet,
        modifier = modifier.fillMaxSize()
    ) {
        val imgBgUrl = "https://uploadstatic.mihoyo.com/contentweb/20200211/2020021114222055628.jpg"
        val headers = remember {
            listOf(
                "https://uploadstatic.mihoyo.com/contentweb/20220127/2022012718344593599.png",
                "https://uploadstatic.mihoyo.com/contentweb/20220127/2022012718350213870.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200306/2020030617000181697.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200306/2020030617001674227.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200306/2020030617005720579.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200306/2020030617015669833.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200306/2020030617025961375.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200306/2020030617031747812.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200306/2020030617033181769.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200312/2020031219222320482.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200315/2020031516092797889.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200330/2020033019074664412.png",
            )
        }
        val bigs = remember {
            listOf(
                "https://webstatic.mihoyo.com/upload/contentweb/2022/06/30/b51565c6f1298e534e90b6e63332e9c1_6618231443033589469.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/07/04/6f0ef40157e95b0d59455c12f4d3f270_3262958961633311108.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/07/04/43922f5162840c215638affedad0459a_154157936806604489.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/07/04/cbce61ef1ee5586c4e77b2070348685f_2873159962263599144.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/07/04/6c009f0631eb71e697c2da76b608a51e_1586187959203635452.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/06/30/f5ae62eff2cf426e98626c882dd0cf0d_3491810425996357724.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/06/30/a1594317bbd0cefeb7da501f9879375b_6718910479380394402.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/06/30/965e940e6caafe8fbd4bf0e17653000b_7797376782783229067.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/06/30/16cff1c00f651f98427fc3fbab7fc855_1013342926215826750.png",
                "https://uploadstatic.mihoyo.com/contentweb/20200312/2020031219451784892.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/06/30/c8a5da498d29faa1c8f2e2bfb60efbd5_7098200614657426672.png",
                "https://webstatic.mihoyo.com/upload/contentweb/2022/06/30/96297a7f2679bf0dce4fb9d11120b882_7601596078607350699.png",
            )
        }
        // 背景图片
        AsyncImage(
            modifier = Modifier
                .layoutId("img_bg")
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = imgBgUrl,
            contentDescription = null
        )
        var selectIndex by remember { mutableIntStateOf(0) }
        val headerCarouselState = rememberCarouselState(selectIndex) { headers.size }
        val bigCarouselState = rememberCarouselState(selectIndex) { headers.size }
        // 大图轮播
        HorizontalUncontainedCarousel(
            state = bigCarouselState,
            modifier = Modifier
                .layoutId("crl_big")
                .fillMaxSize(),
            itemSpacing = 8.dp,
            itemWidth = Int.MAX_VALUE.dp,
            contentPadding = PaddingValues(horizontal = 0.dp),
        ) { index ->
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = bigs[index],
                contentDescription = null
            )
        }
        // 头图轮播
        HorizontalMultiBrowseCarousel(
            state = headerCarouselState,
            modifier = Modifier
                .layoutId("crl_header")
                .fillMaxWidth()
                .height(120.dp)
                .padding(top = 16.dp, bottom = 16.dp),
            preferredItemWidth = 100.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 0.dp),
        ) { index ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        selectIndex = index
                    },
                contentScale = ContentScale.Crop,
                model = headers[index],
                contentDescription = null
            )
        }

    }
}

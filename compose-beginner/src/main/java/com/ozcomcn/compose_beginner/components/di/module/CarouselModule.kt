package com.ozcomcn.compose_beginner.components.di.module

import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.components.CarouselScreen
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object Carousel : BaseNavKey(
    "轮播图",
    "Carousels show a collection of items that can be scrolled on and off the screen",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwur4ddz-3.png?alt=media&token=a42f76c5-c4fd-46fd-89a1-04b492638986"
) // 轮播图


@InstallIn(ViewModelComponent::class)
@Module
interface CarouselModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Carousel): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Carousel = Carousel

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<Carousel> {
                CarouselScreen()
            }
        }

    }

}
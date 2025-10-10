package com.ozcomcn.compose_beginner.animation.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.animation.AnimationScreen
import com.ozcomcn.compose_beginner.animation.di.quelifier.AnimationNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object Animation : BaseNavKey(
    "动画",
    icon = Icons.Default.Image,
    order = 4,
    isMainNavKey = true
)


@InstallIn(ViewModelComponent::class)
@Module
interface AnimationModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@AnimationNavQualifier navKey: Animation): NavKey

    companion object {

        @AnimationNavQualifier
        @Provides
        fun providesNavKey(): Animation = Animation

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<Animation> {
                AnimationScreen()
            }
        }

    }

}
package com.ozcomcn.compose_beginner.components.di.module

import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.components.ButtonsScreen
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet


data object Buttons : BaseNavKey(
    "按钮",
    "When choosing the right button for an action, consider the level of emphasis each button type provides",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4kdl8gi-1.png?alt=media&token=78222465-efb0-405d-9e25-3a771b475e3a"
) // 按钮


@InstallIn(ViewModelComponent::class)
@Module
interface ButtonsModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Buttons): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Buttons = Buttons

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Buttons> {
                ButtonsScreen()
            }
        }

    }

}
package com.ozcomcn.compose_beginner.text.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TextFields
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import com.ozcomcn.compose_beginner.text.TextScreen
import com.ozcomcn.compose_beginner.text.di.quelifier.TextNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet
data object Text : BaseNavKey(
    "文本",
    icon = Icons.Default.TextFields,
    order = 3,
    isMainNavKey = true
)



@InstallIn(ViewModelComponent::class)
@Module
interface TextModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@TextNavQualifier navKey: Text): NavKey

    companion object {

        @TextNavQualifier
        @Provides
        fun providesNavKey(): Text = Text

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Text> {
                TextScreen()
            }
        }

    }

}
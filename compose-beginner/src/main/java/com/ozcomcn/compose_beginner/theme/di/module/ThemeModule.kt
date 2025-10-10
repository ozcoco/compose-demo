package com.ozcomcn.compose_beginner.theme.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Style
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import com.ozcomcn.compose_beginner.theme.ThemeScreen
import com.ozcomcn.compose_beginner.theme.di.quelifier.ThemeNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object Theme : BaseNavKey(
    "主题",
    icon = Icons.Default.Style,
    order = 5,
    isMainNavKey = true
)


@InstallIn(ViewModelComponent::class)
@Module
interface ThemeModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@ThemeNavQualifier navKey: Theme): NavKey

    companion object {

        @ThemeNavQualifier
        @Provides
        fun providesNavKey(): Theme = Theme

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<Theme> {
                ThemeScreen()
            }
        }

    }

}
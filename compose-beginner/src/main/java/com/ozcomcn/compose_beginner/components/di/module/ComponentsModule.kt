package com.ozcomcn.compose_beginner.components.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Widgets
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.components.ComponentsScreen
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object Components : BaseNavKey(
    "组件列表",
    icon = Icons.Default.Widgets,
    order = 0,
    isMainNavKey = true
)

@InstallIn(ViewModelComponent::class)
@Module
interface ComponentsModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@ComponentsNavQualifier navKey: Components): NavKey

    companion object {

        @ComponentsNavQualifier
        @Provides
        fun providesNavKey(): Components = Components

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Components> {
                ComponentsScreen()
            }
        }

    }

}
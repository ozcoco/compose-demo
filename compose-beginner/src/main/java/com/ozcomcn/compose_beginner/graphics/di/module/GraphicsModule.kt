package com.ozcomcn.compose_beginner.graphics.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.graphics.GraphicsScreen
import com.ozcomcn.compose_beginner.graphics.di.quelifier.GraphicsNavQualifier
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object Graphics : BaseNavKey(
    "图形",
    icon = Icons.Default.Image,
    order = 2,
    isMainNavKey = true
)


@InstallIn(ViewModelComponent::class)
@Module
interface GraphicsModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@GraphicsNavQualifier navKey: Graphics): NavKey

    companion object {

        @GraphicsNavQualifier
        @Provides
        fun providesNavKey(): Graphics = Graphics

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<Graphics> {
                GraphicsScreen()
            }
        }

    }

}
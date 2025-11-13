package com.ozcomcn.compose_beginner.layouts.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Layers
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.layouts.LayoutScreen
import com.ozcomcn.compose_beginner.layouts.di.quelifier.LayoutsNavQualifier
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet
data object Layouts : BaseNavKey(
    "布局",
    icon = Icons.Default.Layers,
    order = 1,
    isMainNavKey = true
)


@InstallIn(ViewModelComponent::class)
@Module
interface LayoutsModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@LayoutsNavQualifier navKey: Layouts): NavKey

    companion object {

        @LayoutsNavQualifier
        @Provides
        fun providesNavKey(): Layouts = Layouts

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Layouts> {
                LayoutScreen()
            }
        }

    }

}
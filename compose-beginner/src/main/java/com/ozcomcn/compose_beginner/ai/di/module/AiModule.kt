package com.ozcomcn.compose_beginner.ai.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.ai.AiScreen
import com.ozcomcn.compose_beginner.ai.di.quelifier.AINavQualifier
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

data object AI : BaseNavKey(
    "AI",
    icon = Icons.Default.AutoAwesome,
    order = 6,
    isMainNavKey = true
)


@Module
@InstallIn(ViewModelComponent::class)
interface AIModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@AINavQualifier navKey: AI): NavKey

    companion object {

        @AINavQualifier
        @Provides
        fun providesNavKey(): AI = AI

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<AI> {
                AiScreen()
            }
        }

    }

}
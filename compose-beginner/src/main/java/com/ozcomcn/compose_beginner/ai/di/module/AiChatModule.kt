package com.ozcomcn.compose_beginner.ai.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.ai.AiChatScreen
import com.ozcomcn.compose_beginner.ai.di.quelifier.AINavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object AIChat : BaseNavKey(
    "AIChat",
    icon = Icons.AutoMirrored.Filled.Chat,
)


@Module
@InstallIn(ViewModelComponent::class)
interface AIChatModule {

    @MainNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(@AINavQualifier navKey: AIChat): NavKey

    companion object {

        @AINavQualifier
        @Provides
        fun providesNavKey(): AIChat = AIChat

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<AIChat> {
                AiChatScreen()
            }
        }

    }

}
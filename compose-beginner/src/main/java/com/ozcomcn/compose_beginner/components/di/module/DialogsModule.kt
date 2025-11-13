package com.ozcomcn.compose_beginner.components.di.module

import androidx.compose.material3.Text
import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object Dialogs : BaseNavKey(
    "对话框",
    "Dialogs provide important prompts in a user flow",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8qzxs3f-01.png?alt=media&token=6abae319-6bab-4fa0-8612-8e4f4e0246c7"
) //对话框


@InstallIn(ViewModelComponent::class)
@Module
interface DialogsModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Dialogs): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Dialogs = Dialogs

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Dialogs> {
                Text(it.title)
            }
        }

    }

}
package com.ozcomcn.compose_beginner.components.di.module

import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.base.nav.EntryProviderScope
import com.ozcomcn.compose_beginner.components.TextFieldsScreen
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet


data object TextFields : BaseNavKey(
    "输入字段",
    "Text fields let users enter text into a UI",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flx2u2h3p-1.png?alt=media&token=71a990c4-03bd-4c34-afc6-130018ed186e"
) //导航


@InstallIn(ViewModelComponent::class)
@Module
interface TextFieldsModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: TextFields): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): TextFields = TextFields

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<TextFields> {
                TextFieldsScreen()
            }
        }

    }

}
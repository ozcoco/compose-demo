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


data object Lists : BaseNavKey(
    "列表",
    "Lists are continuous, vertical indexes of text and images",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwoqolbn-1.png?alt=media&token=046fb47b-0212-43c1-85d1-e321c8b98351"
) //列表


@InstallIn(ViewModelComponent::class)
@Module
interface ListsModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Lists): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Lists = Lists

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Lists> {
                Text(it.title)
            }
        }

    }

}
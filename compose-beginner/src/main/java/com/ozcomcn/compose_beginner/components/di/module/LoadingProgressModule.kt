package com.ozcomcn.compose_beginner.components.di.module

import androidx.compose.material3.Text
import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object LoadingProgress : BaseNavKey(
    "加载进度条",
    "Loading indicators show the progress for a short wait time Resources",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0c2einw-03.png?alt=media&token=ee7d6244-1b86-4896-85b7-476ce06b10aa"
) //加载进度条


@InstallIn(ViewModelComponent::class)
@Module
interface LoadingProgressModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: LoadingProgress): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): LoadingProgress = LoadingProgress

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<LoadingProgress> {
                Text(it.title)
            }
        }

    }

}
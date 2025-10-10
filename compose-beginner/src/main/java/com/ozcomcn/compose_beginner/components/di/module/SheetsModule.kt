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


data object Sheets : BaseNavKey(
    "底部弹窗",
    "Bottom sheets show secondary content anchored to the bottom of the screen",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flvp3m82k-1.png?alt=media&token=57a51bb9-a0b7-40f2-b057-0afe7d122a59"
) //导航


@InstallIn(ViewModelComponent::class)
@Module
interface SheetsModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Sheets): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Sheets = Sheets

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<Sheets> {
                Text(it.title)
            }
        }

    }

}
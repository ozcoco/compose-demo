package com.ozcomcn.compose_beginner.components.di.module

import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.components.AppBarsScreen
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object AppBars : BaseNavKey(
    "标题栏",
    "App bars are placed at the top of the screen to help people navigate through a product",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmaaqdf2b-01.png?alt=media&token=84877227-b485-453e-9c03-2cfa90804f16"
)


@InstallIn(ViewModelComponent::class)
@Module
interface AppBarsModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: AppBars): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): AppBars = AppBars

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<AppBars> {
                AppBarsScreen()
            }
        }

    }

}
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


data object Menus : BaseNavKey(
    "菜单",
    "Menus display a list of choices on a temporary surface",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flvozu1t4-1.png?alt=media&token=19503103-c4d8-4ce7-bb7d-426feb8a84a3"
) //菜单


@InstallIn(ViewModelComponent::class)
@Module
interface MenusModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Menus): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Menus = Menus

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Menus> {
                Text(it.title)
            }
        }

    }

}
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

data object Divider : BaseNavKey(
    "分隔线",
    "Dividers are thin lines that group content in lists or other containers",
    "https://lh3.googleusercontent.com/nt6TfZJ1hPz6HX6Pn1Lvf2_jMgg3cnIGfttwCupUGgry6V5DHR1JvdSdkkWIUdVopwjJ_BRzYTaANVIo4AczkVo0jKYD3B1J8NShIWhVlYY=s0"
) //分隔线


@InstallIn(ViewModelComponent::class)
@Module
interface DividerModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Divider): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Divider = Divider

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<Divider> {
                Text(it.title)
            }
        }

    }

}
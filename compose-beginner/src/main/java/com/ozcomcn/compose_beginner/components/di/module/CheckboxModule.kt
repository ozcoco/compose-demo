package com.ozcomcn.compose_beginner.components.di.module

import androidx.navigation3.runtime.entry
import com.ozcomcn.compose_beginner.base.nav.EntryProviderInstaller
import com.ozcomcn.compose_beginner.components.CheckboxScreen
import com.ozcomcn.compose_beginner.components.di.qualifier.ComponentsNavQualifier
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

data object Checkbox : BaseNavKey(
    "复选框",
    "Checkboxes let users select one or more items from a list, or turn an item on or off",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmabau8sl-1.png?alt=media&token=e7a6af49-16e0-4d76-9931-131edb8e672f"
) // 复选框


@InstallIn(ViewModelComponent::class)
@Module
interface CheckboxModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: Checkbox): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): Checkbox = Checkbox

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderInstaller = {
            entry<Checkbox> {
                CheckboxScreen()
            }
        }

    }

}
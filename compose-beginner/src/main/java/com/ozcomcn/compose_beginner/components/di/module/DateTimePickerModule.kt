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

data object DateTimePicker : BaseNavKey(
    "日期时间选择器",
    "Time pickers help users select and set a specific time",
    "https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flw8w5mgq-1.png?alt=media&token=291c08e3-8321-4f33-a4fc-a6b8703fb100"
) //日期时间选择器


@InstallIn(ViewModelComponent::class)
@Module
interface DateTimePickerModule {

    @ComponentsNavQualifier
    @IntoSet
    @Binds
    fun providesDestination(navKey: DateTimePicker): BaseNavKey

    companion object {

        @Provides
        fun providesNavKey(): DateTimePicker = DateTimePicker

        @ViewModelScoped
        @IntoSet
        @Provides
        fun providesEntryProviderInstaller(): EntryProviderScope = {
            entry<DateTimePicker> {
                Text(it.title)
            }
        }

    }

}
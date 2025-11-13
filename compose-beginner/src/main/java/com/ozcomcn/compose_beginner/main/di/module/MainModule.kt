package com.ozcomcn.compose_beginner.main.di.module

import android.content.Context
import androidx.navigation3.runtime.NavKey
import com.ozcomcn.compose_beginner.base.nav.Navigator
import com.ozcomcn.compose_beginner.components.di.module.Components
import com.ozcomcn.compose_beginner.main.di.qualifier.MainNavQualifier
import com.ozcomcn.compose_beginner.main.di.qualifier.MainStartDestinationQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
interface MainModule {

    @ActivityRetainedScoped
    @MainStartDestinationQualifier
    @Binds
    fun providesMainStartDestination(@MainNavQualifier navKey: Components): NavKey

    companion object {

        @MainNavQualifier
        @Provides
        fun providesMainStartNavKey(): Components = Components

        @ActivityRetainedScoped
        @MainNavQualifier
        @Provides
        fun providesMainNavigator(@MainStartDestinationQualifier startDestination: NavKey) =
            Navigator(startDestination)

    }

}
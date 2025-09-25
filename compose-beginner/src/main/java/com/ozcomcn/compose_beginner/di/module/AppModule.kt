package com.ozcomcn.compose_beginner.di.module

import com.ozcomcn.compose_beginner.nav.Navigator
import com.ozcomcn.compose_beginner.ui.home.Screen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    @ActivityRetainedScoped
    fun provideNavigator(): Navigator = Navigator(startDestination = Screen.ComponentList)

}
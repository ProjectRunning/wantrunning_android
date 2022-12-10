package com.openrun.wantrunning.di

import com.openrun.wantrunning.feature.home.PartyListAdapter
import com.openrun.wantrunning.feature.home.HomeNavigator
import com.openrun.wantrunning.feature.landing.signin.SignInButtonClick
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun provideSignInButtonClick(signInButtonClick: SignInButtonClickImpl): SignInButtonClick

    @Binds
    abstract fun provideHomeNavigator(homeNavigator: HomeNavigatorImpl): HomeNavigator

    @Binds
    abstract fun providePartyListItemClick(
        onPartyListItemClickListener: OnPartyListItemClickListenerImpl
    ): PartyListAdapter.OnPartyListItemClickListener
}
package ca.nick.redditcoroutines.di

import ca.nick.redditcoroutines.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity
}
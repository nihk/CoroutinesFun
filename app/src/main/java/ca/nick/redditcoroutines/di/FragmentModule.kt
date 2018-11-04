package ca.nick.redditcoroutines.di

import ca.nick.redditcoroutines.ui.FrontPageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun frontPageFragment(): FrontPageFragment
}
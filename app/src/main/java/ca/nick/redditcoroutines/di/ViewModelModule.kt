package ca.nick.redditcoroutines.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.nick.redditcoroutines.vm.FrontPageViewModel
import ca.nick.redditcoroutines.vm.VmFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NetworkingModule::class, LocalDataModule::class])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: VmFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FrontPageViewModel::class)
    abstract fun bindFrontPageViewModel(frontPageViewModel: FrontPageViewModel) : ViewModel
}
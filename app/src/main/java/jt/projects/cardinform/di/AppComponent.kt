package jt.projects.cardinform.di

import dagger.Component
import jt.projects.cardinform.ui.MainActivity
import jt.projects.cardinform.viewmodel.CardViewModel
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class, RemoteRepoModule::class, LocalRepoModule::class, RoomModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainViewModel: CardViewModel)
}
package jt.projects.cardinform.di

import dagger.Module
import dagger.Provides
import jt.projects.cardinform.repos.local.AppDatabase
import jt.projects.cardinform.repos.local.CardsRepoRoomImpl
import jt.projects.cardinform.repos.local.ICardsLocalRepository
import javax.inject.Singleton

@Module
class LocalRepoModule {

    @Singleton
    @Provides
    fun cardLocalRepo(
        database: AppDatabase
    ): ICardsLocalRepository =
        CardsRepoRoomImpl(database)

}
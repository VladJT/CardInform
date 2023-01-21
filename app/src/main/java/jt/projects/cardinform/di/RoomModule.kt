package jt.projects.cardinform.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import jt.projects.cardinform.App
import jt.projects.cardinform.BuildConfig
import jt.projects.cardinform.repos.local.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun database(app: App): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
}
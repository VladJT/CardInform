package jt.projects.cardinform.di

import dagger.Module
import dagger.Provides
import jt.projects.cardinform.App
import javax.inject.Singleton


@Module
class AppModule(private val app: App) {
    @Singleton
    @Provides
    fun app(): App {
        return app
    }
}
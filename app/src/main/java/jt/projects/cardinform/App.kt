package jt.projects.cardinform

import android.app.Application
import jt.projects.cardinform.di.AppComponent
import jt.projects.cardinform.di.AppModule
import jt.projects.cardinform.di.DaggerAppComponent


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
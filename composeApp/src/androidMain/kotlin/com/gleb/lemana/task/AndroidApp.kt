package com.gleb.lemana.task

import android.app.Application
import com.gleb.lemana.task.data.database.di.dbModule
import com.gleb.lemana.task.data.network.di.networkModule
import com.gleb.lemana.task.presentation.di.presentationModule
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        startKoin {
            modules(networkModule, presentationModule, dbModule)
        }
    }
}

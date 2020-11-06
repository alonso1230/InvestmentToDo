package com.example.investmenttodo.app

import android.app.Application
import androidx.room.Room
import com.example.investmenttodo.database.AppDatabase
import com.example.investmenttodo.util.Prefs

class App : Application() {

    val prefs: Prefs by lazy { Prefs(this) }
    val database: AppDatabase by lazy { Room.databaseBuilder(this, AppDatabase::class.java, "database").build() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}
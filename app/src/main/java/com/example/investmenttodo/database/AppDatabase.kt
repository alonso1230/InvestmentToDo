package com.example.investmenttodo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.investmenttodo.dataclass.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
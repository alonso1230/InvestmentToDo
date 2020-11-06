package com.example.investmenttodo.database

import androidx.room.*
import com.example.investmenttodo.dataclass.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): MutableList<User>

    @Insert
    fun insert(project: User)

    @Update
    fun update(project: User)

    @Delete
    fun delete(project: User)

}
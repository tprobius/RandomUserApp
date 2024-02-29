package com.tprobius.randomuserapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tprobius.randomuserapp.data.entities.RandomUserEntity

@Database(entities = [RandomUserEntity::class], version = 1, exportSchema = false)
abstract class RandomUserDatabase : RoomDatabase() {
    abstract val randomUserDao: RandomUserDao

    companion object {
        const val DATABASE_NAME = "random_user_db"
    }
}
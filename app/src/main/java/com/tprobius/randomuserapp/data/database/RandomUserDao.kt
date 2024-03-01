package com.tprobius.randomuserapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tprobius.randomuserapp.data.entities.RandomUserEntity

@Dao
interface RandomUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: RandomUserEntity)


    @Transaction
    fun insertUsersList(userList: List<RandomUserEntity>) {
        deleteUsersList()
        for (user in userList) {
            insertUser(user)
        }
    }

    @Query("SELECT * FROM users")
    fun getUsersList(): List<RandomUserEntity>

    @Query("DELETE FROM users")
    fun deleteUsersList()
}
package com.t3k.mobile.fams.app.roomdatabase.daos

import androidx.room.*
import com.t3k.mobile.fams.app.roomdatabase.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM tbl_user")
    suspend fun getAllUser(): List<UserEntity>


    @Query("DELETE FROM tbl_user")
    suspend fun deleteAllUser()
}
package com.t3k.mobile.fams.app.roomdatabase.daos

import androidx.room.*
import com.t3k.mobile.fams.app.roomdatabase.entities.LoginHistoryEntity

@Dao
interface LoginHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLoginHistory(loginHistoryEntity: LoginHistoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLoginHistory(loginHistoryEntity: LoginHistoryEntity)

    @Delete
    suspend fun deleteLoginHistory(loginHistoryEntity: LoginHistoryEntity)

    @Query("SELECT * FROM tbl_login_history")
    suspend fun getAllLoginHistory(): List<LoginHistoryEntity>


    @Query("DELETE FROM tbl_login_history")
    suspend fun deleteAllLoginHistory()
}
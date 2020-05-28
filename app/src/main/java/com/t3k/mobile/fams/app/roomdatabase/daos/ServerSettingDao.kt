package com.t3k.mobile.fams.app.roomdatabase.daos

import androidx.room.*
import com.t3k.mobile.fams.app.roomdatabase.entities.ServerSettingEntity

@Dao
interface ServerSettingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertServerSetting(serverSettingEntity: ServerSettingEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateServerSetting(serverSettingEntity: ServerSettingEntity)

    @Delete
    suspend fun deleteServerSetting(serverSettingEntity: ServerSettingEntity)

    @Query("SELECT * FROM tbl_server_setting")
    suspend fun getAllServerSetting(): List<ServerSettingEntity>

    @Query("SELECT * FROM tbl_server_setting WHERE protocol=:Protocol AND address=:Address AND context_path=:contextPath")
     fun getExistingServerSetting(
        Protocol: Byte,
        Address: String,
        contextPath: String
    ):ServerSettingEntity?

    @Query("SELECT * FROM tbl_server_setting WHERE setting_id = :ID")
     fun getServerSetting(ID:Int) : ServerSettingEntity?


    @Query("DELETE FROM tbl_server_setting")
    suspend fun deleteAllServerSetting()
}
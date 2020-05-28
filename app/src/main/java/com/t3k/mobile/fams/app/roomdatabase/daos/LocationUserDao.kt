package com.t3k.mobile.fams.app.roomdatabase.daos

import androidx.room.*
import com.t3k.mobile.fams.app.roomdatabase.entities.LocationUserEntity

@Dao
interface LocationUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocationUser(locationUserEntity: LocationUserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLocationUser(locationUserEntity: LocationUserEntity)

    @Delete
    suspend fun deleteLocationUser(locationUserEntity: LocationUserEntity)

    @Query("SELECT * FROM tbl_location_user")
    suspend fun getAllLocationUser(): List<LocationUserEntity>


    @Query("DELETE FROM tbl_location_user")
    suspend fun deleteAllLocationUser()
}
package com.t3k.mobile.fams.app.roomdatabase.daos

import androidx.room.*
import com.t3k.mobile.fams.app.roomdatabase.entities.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocation(locationEntity: LocationEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLocation(locationEntity: LocationEntity)

    @Delete
    suspend fun deleteLocation(locationEntity: LocationEntity)

    @Query("SELECT * FROM tbl_location")
    suspend fun getAllLocation(): List<LocationEntity>


    @Query("DELETE FROM tbl_location")
    suspend fun deleteAllLocation()
}
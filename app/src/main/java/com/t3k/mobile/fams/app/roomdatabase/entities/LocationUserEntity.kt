package com.t3k.mobile.fams.app.roomdatabase.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "tbl_location_user"
)
data class LocationUserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "location_id") val location_id: Int,
    @ColumnInfo(name = "user_id") val userId: Int


) : Parcelable
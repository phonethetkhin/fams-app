package com.t3k.mobile.fams.app.roomdatabase.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "tbl_location", indices = [Index(value = ["server_id"], unique = true)]
)
data class LocationEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "server_id")
    val serverId: Int,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String


) : Parcelable
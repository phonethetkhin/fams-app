package com.t3k.mobile.fams.app.roomdatabase.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "tbl_server_setting", indices = [Index(value = ["setting_id"], unique = true)]
)
data class ServerSettingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "setting_id")
    val setting_id: Int,
    @ColumnInfo(name = "protocol") val protocol: Byte,
    @ColumnInfo(name = "address_type") val address_type: Byte,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "context_path") val context_path: String,
    @ColumnInfo(name = "status") val status: Byte


) : Parcelable
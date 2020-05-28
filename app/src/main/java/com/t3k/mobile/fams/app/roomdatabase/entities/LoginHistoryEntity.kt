package com.t3k.mobile.fams.app.roomdatabase.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(
    tableName = "tbl_login_history", indices = [Index(value = ["user_id"], unique = true)]
)
data class LoginHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "server_setting_id") val serverSettingId: Int,
    @ColumnInfo(name = "login_time") val loginTime: String,
    @ColumnInfo(name = "status") val status: Int


) : Parcelable
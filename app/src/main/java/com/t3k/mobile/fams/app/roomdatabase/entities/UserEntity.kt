package com.t3k.mobile.fams.app.roomdatabase.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "tbl_user", indices = [Index(value = ["server_id", "content_id"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "server_id")
    val serverId: Int,
    @ColumnInfo(name = "content_id") val contentId: Int,
    @ColumnInfo(name = "login_id") val loginId: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "last_sync") val lastSync: String


) : Parcelable
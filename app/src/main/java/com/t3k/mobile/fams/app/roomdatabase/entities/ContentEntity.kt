package com.t3k.mobile.fams.app.roomdatabase.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "tbl_content", indices = [Index(value = ["id"], unique = true)]
)
data class ContentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "content_path") val contentPath: String,
    @ColumnInfo(name = "server_id") val serverId: Int


) : Parcelable
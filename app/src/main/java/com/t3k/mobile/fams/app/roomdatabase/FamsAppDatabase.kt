package com.t3k.mobile.fams.app.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.t3k.mobile.fams.app.roomdatabase.daos.*
import com.t3k.mobile.fams.app.roomdatabase.entities.*

@Database(
    entities = [ServerSettingEntity::class, ContentEntity::class, LocationEntity::class, UserEntity::class, LocationUserEntity::class, LoginHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FamsAppDatabase : RoomDatabase() {
    abstract fun serverSettingDao(): ServerSettingDao
    abstract fun contentDao(): ContentDao
    abstract fun locationDao(): LocationDao
    abstract fun userDao(): UserDao
    abstract fun locationUserDao(): LocationUserDao
    abstract fun loginHistoryDao(): LoginHistoryDao

    companion object {
        @Volatile
        var INSTANCE: FamsAppDatabase? = null
        fun getFAMSDB(context: Context): FamsAppDatabase? {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE =
                        Room.databaseBuilder(context, FamsAppDatabase::class.java, "fams_app.db")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
    }

    fun destoryFAMSDB() {
        INSTANCE = null
    }

}
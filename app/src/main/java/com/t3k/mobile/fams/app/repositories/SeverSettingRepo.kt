package com.t3k.mobile.fams.app.repositories

import androidx.lifecycle.MutableLiveData
import com.t3k.mobile.fams.app.roomdatabase.daos.ServerSettingDao
import com.t3k.mobile.fams.app.roomdatabase.entities.ServerSettingEntity

class SeverSettingRepo(private val serverSettingDao: ServerSettingDao) {
    val serverSettingLiveDataList = MutableLiveData<List<ServerSettingEntity>>()
    val serverSettingLiveData = MutableLiveData<ServerSettingEntity>()


    suspend fun getAllServerSetting() {
        serverSettingLiveDataList.postValue(serverSettingDao.getAllServerSetting())
    }


    suspend fun insertServerSetting(serverSettingEntity: ServerSettingEntity) {
        serverSettingDao.insertServerSetting(serverSettingEntity)
    }

    suspend fun updateServerSetting(serverSettingEntity: ServerSettingEntity) {
        serverSettingDao.updateServerSetting(serverSettingEntity)
    }

    suspend fun deleteServerSetting(serverSettingEntity: ServerSettingEntity) {
        serverSettingDao.deleteServerSetting(serverSettingEntity)
    }

    suspend fun deleteAllServerSetting() {
        serverSettingDao.deleteAllServerSetting()
    }

     fun getServerSetting(id:Int)
    {
        serverSettingLiveData.postValue(serverSettingDao.getServerSetting(id))
    }


}
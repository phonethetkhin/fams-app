@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.t3k.mobile.fams.app.repositories

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.t3k.mobile.fams.app.model.DeviceModel
import com.t3k.mobile.fams.app.retrofit.Retrofitobj
import com.t3k.mobile.fams.app.ui.GetLicenseActivity
import com.t3k.mobile.fams.app.ui.GetRegisterActivity
import com.t3k.mobile.fams.app.ui.LoginActivity
import com.t3k.mobile.fams.app.utility.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckDeviceInfoRepo(val app:FragmentActivity) {
    private val apiService = Retrofitobj(getBaseURL(app)).apiservice
    var license = false


    fun checkRegister(
        device: DeviceModel?,
        callback: Callback<Map<String?, Any?>?>?
    ) {
        if (device != null) {
            apiService
                .checkRegister(APP_TYPE, device)!!.enqueue(callback)
        }
    }

    fun checkDeviceInfo() {
        val devCode = getStringPref(app,"devicecode","devicecode","").toString()
        val devName = getStringPref(app,"devicename","devicename","").toString()
        val devInfo = getDeviceInfo(app,devName,devCode)
        checkRegister(
            devInfo,
            object : Callback<Map<String?, Any?>?> {
                override fun onResponse(
                    call: Call<Map<String?, Any?>?>?,
                    response: Response<Map<String?, Any?>?>?
                ) {
                    if (response!!.code() == 200) {
                        val result =
                            response.body()
                        val deviceAppId = result!!["deviceAppId"] as Double

                        setIntPref(app,"deviceappid","deviceappid",deviceAppId.toInt())
                        val deviceState =
                            result["deviceState"] as String?
                        if(deviceState.equals("ACTIVE"))
                        {
                        checkLicense(devInfo)
                        }
                        else if(deviceState.equals("IN_ACTIVE")) {
                            val intent = Intent(app,GetRegisterActivity::class.java)
                            intent.putExtra("status",1)
                            app.startActivity(intent)

                            app.finish()
                        }
                    }
                     else {
                        val intent = Intent(app,GetRegisterActivity::class.java)
                        intent.putExtra("status",0)
                        app.startActivity(intent)

                        app.finish()

                    }
                }

                override fun onFailure(
                    call: Call<Map<String?, Any?>?>?,
                    t: Throwable
                ) {
                    setToast(app,"Can't connect to the server !!!",Toast.LENGTH_SHORT)

                }
            })
    }
    private fun checkLicense(devInfo:DeviceModel)
    {
        val deviceAppID = getIntPref(app,"deviceappid","deviceappid",0)
        val loginID = getStringPref(app,"loginid","loginid","")
        val password = getStringPref(app,"password","password","")
        if(deviceAppID==0)
        {
           val intent = Intent(app,GetRegisterActivity::class.java)
            intent.putExtra("status",0)
            app.startActivity(intent)
            app.finish()

        }
        else {
            val checkLicenseRepo = CheckLicenseRepo(app)
            checkLicenseRepo.checkDeviceLicense(devInfo,loginID,password)
        }




    }

}
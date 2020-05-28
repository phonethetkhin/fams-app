package com.t3k.mobile.fams.app.repositories

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.t3k.mobile.fams.app.model.DeviceModel
import com.t3k.mobile.fams.app.retrofit.Retrofitobj
import com.t3k.mobile.fams.app.ui.GetLicenseActivity
import com.t3k.mobile.fams.app.utility.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeviceRegistrationRepo(val app:FragmentActivity) {
    private val apiService = Retrofitobj(getBaseURL(app)).apiservice
    lateinit var pref:SharedPreferences
    //register device info
    private fun register(
        loginId: String?,
        password: String?,
        device: DeviceModel?,
        callback: Callback<Map<String?, Any?>?>
    ) {
        if (device != null && loginId != null && password != null) {
            apiService.deviceRegister(APP_TYPE, loginId, password, device)!!.enqueue(callback)
        }
    }
    fun registerDeviceInfo(loginId:String, password: String, dModel: DeviceModel) {
        register(loginId, password, dModel,
            object : Callback<Map<String?, Any?>?> {
                override fun onResponse(
                    call: Call<Map<String?, Any?>?>,
                    response: Response<Map<String?, Any?>?>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        val result: Map<String?, Any?>? =
                            response.body()
                        val deviceAppId:Double = result!!["deviceAppId"] as Double
                        val deviceCode = result["deviceCode"]
                        setIntPref(app,"deviceappid","deviceappid",deviceAppId.toInt())
                        setStringPref(app,"devicecode","devicecode",deviceCode.toString())
                        pref= app.getSharedPreferences("user",Context.MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putString("loginid",loginId)
                        editor.putString("password",password)
                        editor.apply()

                        val devCode = getStringPref(app,"devicecode","devicecode","").toString()
                        val devName = getStringPref(app,"devicename","devicename","").toString()
                        val devInfo = getDeviceInfo(app,devName,devCode)


                        setToast(app,"Success",Toast.LENGTH_SHORT)
setBooleanPref(app,"isregister","isregister",true)
                        val checkLicenseRepo = CheckLicenseRepo(app)
                        checkLicenseRepo.checkDeviceLicense(devInfo,loginId,password)

                    } else if (response.code() == 400) {

                        setToast(app,"Invalid Credential",Toast.LENGTH_SHORT)
                    } else {
                        if(response.message() == "Internal Server Error")
                        {

                            setToast(app,"This device is already registered !!!",Toast.LENGTH_SHORT)
                            app.startActivity(Intent(app,GetLicenseActivity::class.java))
                        }

                    }
                }

                override fun onFailure(
                    call: Call<Map<String?, Any?>?>,
                    t: Throwable
                ) {
                    setToast(app,"Can't connect with the server !!!",Toast.LENGTH_SHORT)

                }
            })
    }
}
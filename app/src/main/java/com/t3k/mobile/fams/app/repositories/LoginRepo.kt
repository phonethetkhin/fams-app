package com.t3k.mobile.fams.app.repositories

import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.t3k.mobile.fams.app.retrofit.Retrofitobj
import com.t3k.mobile.fams.app.utility.APP_TYPE
import com.t3k.mobile.fams.app.utility.getBaseURL
import com.t3k.mobile.fams.app.utility.getStringPref
import com.t3k.mobile.fams.app.utility.setToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class LoginRepo(val app:FragmentActivity)
{
    private val apiService = Retrofitobj(getBaseURL(app)).apiservice
    fun login(
        request: Map<String?, Any?>?,
        callback: Callback<Map<String?, Any?>?>
    ) {
        if (request != null) {
            apiService.login(request).enqueue(callback)
        }
    }
    fun userLogin(loginId:String, password : String)
    {
        val deviceCode = getStringPref(app,"devicecode","devicecode","")!!
        val licenseKey = getStringPref(app,"licensekey","licensekey","")!!
        val deviceAppId = getStringPref(app,"deviceappid","deviceappid","")!!

        val request: MutableMap<String?, Any?>? =
            HashMap()
        request!!["appType"] = APP_TYPE
        request["loginId"] = loginId
        request["password"] = password
        request["deviceCode"] = deviceCode
        request["licenseKey"] = licenseKey
        request["deviceAppId"] = deviceAppId

        login(
            request,
            object : Callback<Map<String?, Any?>?> {
                override fun onResponse(
                    call: Call<Map<String?, Any?>?>,
                    response: Response<Map<String?, Any?>?>
                ) {
                    if (response.isSuccessful) {
                       setToast(app,"Success",Toast.LENGTH_SHORT)
                    }
                    else {
                        setToast(app,"Error",Toast.LENGTH_SHORT)
                    }
                }

                override fun onFailure(
                    call: Call<Map<String?, Any?>?>,
                    t: Throwable
                ) {

                    setToast(app,"Failed",Toast.LENGTH_SHORT)

                }
            })
    }
    }

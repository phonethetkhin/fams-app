package com.t3k.mobile.fams.app.repositories

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.t3k.mobile.fams.app.model.DeviceModel
import com.t3k.mobile.fams.app.retrofit.Retrofitobj
import com.t3k.mobile.fams.app.ui.GetLicenseActivity
import com.t3k.mobile.fams.app.ui.LoginActivity
import com.t3k.mobile.fams.app.utility.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckLicenseRepo(val app: FragmentActivity) {
    private val apiService = Retrofitobj(getBaseURL(app)).apiservice

    private fun checkLicense(
        deviceAppId: Int?,
        loginId: String?,
        password: String?,
        device: DeviceModel?,
        callback: Callback<Map<String?, Any?>?>
    ) {
        if (deviceAppId != 0 && loginId != null && password != null && device != null) {
            apiService.checkLicense(APP_TYPE, deviceAppId, true, loginId, password, device)!!
                .enqueue(callback)
        }
    }


    fun checkDeviceLicense(dModel: DeviceModel, loginId: String?, password: String?) {
        val deviceAppId = getIntPref(app, "deviceappid", "deviceappid", 0)
        val info = DeviceModel()
        info.deviceCode = dModel.deviceCode
        info.brand = dModel.brand
        info.model = dModel.model
        info.deviceId1 = dModel.deviceId1
        info.serialNo = dModel.serialNo
        info.androidId = dModel.androidId
        info.fingerPrint = dModel.fingerPrint
        checkLicense(
            deviceAppId,
            loginId,
            password,
            info,
            object : Callback<Map<String?, Any?>?> {
                override fun onResponse(
                    call: Call<Map<String?, Any?>?>,
                    response: Response<Map<String?, Any?>?>
                ) {
                    when {
                        response.code() == 200 -> {

                            val result = response.body()
                            val licenseKey = result!!["licenseKey"].toString()
                            val appStatus = result["appStatus"].toString()
                            when (appStatus) {
                                "NO_LICENSED" -> {
                                    val intent = Intent(app, GetLicenseActivity::class.java)
                                    intent.putExtra("licensestatus", 0)
                                    app.startActivity(intent)
                                    app.finish()

                                }
                                "REQUEST_PENDING" -> {
                                    val intent = Intent(app, GetLicenseActivity::class.java)
                                    intent.putExtra("licensestatus", 1)
                                    app.startActivity(intent)
                                    app.finish()

                                }
                                else -> {

                                    setStringPref(app, "licensekey", "licensekey", licenseKey)
                                    app.startActivity(Intent(app, LoginActivity::class.java))
                                    app.finish()
                                }
                            }

                        }
                        response.code() == 406 -> {

                            val intent =
                                Intent(app, GetLicenseActivity::class.java)
                            intent.putExtra("licensestatus", 2)
                            app.startActivity(intent)
                            app.finish()

                        }
                        else -> {

                            val intent =
                                Intent(app, GetLicenseActivity::class.java)
                            intent.putExtra("licensestatus", 0)
                            app.startActivity(intent)
                            app.finish()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<Map<String?, Any?>?>,
                    t: Throwable
                ) {
                    setToast(app, "Failed", Toast.LENGTH_SHORT)
                }


            })

    }
}

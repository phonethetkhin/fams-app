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

class RequestLicenseRepo(val app: FragmentActivity) {
    private val apiService = Retrofitobj(getBaseURL(app)).apiservice

    private fun license(
        deviceAppId: Int?,
        loginId: String?,
        password: String?,
        device: DeviceModel?,
        callback: Callback<Map<String?, Any?>?>
    ) {
        if (deviceAppId != 0 && loginId != null && password != null && device != null) {
            apiService.licenseRequest(APP_TYPE, deviceAppId, loginId, password, device)!!
                .enqueue(callback)
        }
    }

    fun requestLicense(dModel: DeviceModel, loginId: String, password: String?) {
        val deviceAppId = getIntPref(app, "deviceappid", "deviceappid", 0)


        val devModel = DeviceModel()
        devModel.deviceCode = dModel.deviceCode
        devModel.brand = dModel.brand
        devModel.model = dModel.model
        devModel.deviceId1 = dModel.deviceId1
        devModel.serialNo = dModel.serialNo
        devModel.androidId = dModel.androidId
        devModel.fingerPrint = dModel.fingerPrint

        license(
            deviceAppId,
            loginId,
            password,
            devModel,
            object : Callback<Map<String?, Any?>?> {
                override fun onResponse(
                    call: Call<Map<String?, Any?>?>,
                    response: Response<Map<String?, Any?>?>
                ) {
                    if (response.code() == 200 || response.code() == 202) {
                        val result: Map<String?, Any?>? =
                            response.body()
                        if (result != null) {
                            val status = result["status"].toString()
                            if (status == "REQUEST_PENDING") {
                                val intent = Intent(app, GetLicenseActivity::class.java)
                                intent.putExtra("licensestatus", 1)
                                app.startActivity(intent)
                                app.finish()
                            } else {
                                val licenseKey = result["licenseKey"].toString()

                                setStringPref(app, "licensekey", "licensekey", licenseKey)
                                app.startActivity(Intent(app, LoginActivity::class.java))
                                app.finish()
                            }
                        }
                    } else if (response.code() == 400) {
                        setToast(app, "Invalid Credential", Toast.LENGTH_SHORT)

                    } else {
                        val intent = Intent(app, GetLicenseActivity::class.java)
                        intent.putExtra("licensestatus", 3)
                        app.startActivity(intent)
                        app.finish()
                    }
                }

                override fun onFailure(
                    call: Call<Map<String?, Any?>?>,
                    t: Throwable
                ) {
                    setToast(app, t.message, Toast.LENGTH_SHORT)

                }
            })
    }
}

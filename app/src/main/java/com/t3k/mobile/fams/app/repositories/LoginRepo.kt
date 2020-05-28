package com.t3k.mobile.fams.app.repositories

import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.t3k.mobile.fams.app.model.UserModel
import com.t3k.mobile.fams.app.retrofit.Retrofitobj
import com.t3k.mobile.fams.app.utility.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class LoginRepo(val app:FragmentActivity) {
    val apiService = Retrofitobj(getBaseURL(app)).apiservice
    fun login(
        request: Map<String?, Any?>?,
        callback: Callback<Map<String?, Any?>?>
    ) {
        if (request != null) {
            apiService.login(request).enqueue(callback)
        }
    }

    fun userLogin(loginId:String, password : String) {
        val request: MutableMap<String?, Any?> =
            HashMap()
        val deviceAppId = getIntPref(app,"deviceappid","deviceappid",0)
        val deviceCode = getStringPref(app,"devicecode","devicecode","")
        val licenseKey = getStringPref(app,"licensekey","licensekey","")

        request["appType"] = APP_TYPE
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
                        var userStatus = 0
                        val result =
                            response.body()
                        val userId = result!!["userId"] as Double
                        val token = result["token"] as String?
                        val name = result["userName"].toString()

                        val c = Calendar.getInstance()
                        val dateFormat = SimpleDateFormat(
                            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                            Locale.getDefault()
                        )
                        val lastSync = dateFormat.format(c.time)
                        if (helper.userExists(1)) {
                            try {
                                val users: User = helper.getUserByLoginId(1)
                                if (!users.getLoginId().equals(loginId)) {
                                    userStatus = 1
                                }
                                users.setUserId(userId.toInt())
                                users.setLoginId(loginId)
                                users.setPassword(EncryptionDecryption.encrypt(password))
                                users.setName(name)
                                users.setLastLoginDate(lastSync)
                                helper.updateUser(users)
                                helper.close()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        } else {
                            try {
                                val users = UserModel()
                                users.userId=userId.toInt()
                                users.loginId=loginId
                                users.setPassword(EncryptionDecryption.encrypt(password))
                                users.setName(name)
                                users.setJoinedDate(lastSync)
                                helper.insertUser(users)
                                helper.close()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        pDialog.dismiss()
                        val intent =
                            Intent(getApplicationContext(), HomeActivity::class.java)
                        intent.putExtra("userStatus", userStatus)
                        intent.putExtra("download", 1)
                        prefs = getSharedPreferences(prefName, MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = prefs.edit()
                        editor.putString("loginId", loginId)
                        editor.putString("token", token)
                        editor.apply()
                        startActivity(intent)
                        finish()
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

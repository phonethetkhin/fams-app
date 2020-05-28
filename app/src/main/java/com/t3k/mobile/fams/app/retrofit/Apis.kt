package com.t3k.mobile.fams.app.retrofit

import com.t3k.mobile.fams.app.model.DeviceModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Apis {
    @GET("con/check")
    fun checkServerConnection(@Query("appType") appType: String?): Call<Void?>?

    @POST("dev/check")
    fun checkRegister(
        @Query("appType") appType: String?,
        @Body device: DeviceModel?
    ): Call<Map<String?, Any?>?>?

    @POST("dev/register")
    fun deviceRegister(
        @Query("appType") appType: String?,
        @Query("loginId") loginId: String?,
        @Query("password") password: String?,
        @Body requestInfo: DeviceModel?
    ): Call<Map<String?, Any?>?>?

    @POST("license/check")
    fun checkLicense(
        @Query("appType") appType: String?,
        @Query("deviceAppId") deviceAppId: Int?,
        @Query("isRequested") isRequested: Boolean,
        @Query("loginId") loginId: String?,
        @Query("password") password: String?,
        @Body requestedInfo: DeviceModel?
    ): Call<Map<String?, Any?>?>?

    @POST("license/request")
    fun licenseRequest(
        @Query("appType") appType: String?,
        @Query("deviceAppId") deviceAppId: Int?,
        @Query("loginId") loginId: String?,
        @Query("password") password: String?,
        @Body requestedInfo: DeviceModel?
    ): Call<Map<String?, Any?>?>?

    @POST("usr/login")
    fun login(@Body request: Map<String?, Any?>?): Call<Map<String?, Any?>?>
}
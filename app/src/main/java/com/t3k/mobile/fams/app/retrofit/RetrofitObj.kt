@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.t3k.mobile.fams.app.retrofit

import android.content.Context
import com.t3k.mobile.fams.app.utility.getBaseURL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofitobj(baseURL:String?) {

    val apiservice: Apis

    init {

        val client = OkHttpClient.Builder().build()

        apiservice = Retrofit.Builder().baseUrl(baseURL)

            .addConverterFactory(GsonConverterFactory.create())

            .client(client)

            .build()

            .create(Apis::class.java)

    }
}
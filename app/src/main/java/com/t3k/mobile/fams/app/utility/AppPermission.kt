package com.t3k.mobile.fams.app.utility

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

object AppPermission {
    var permissions = arrayOf(
        Manifest.permission.BLUETOOTH,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.INTERNET
    )

    fun checkPermission(context: Context): Boolean {
        var result: Int
        val listPermissionsNeeded: MutableList<String> =
            ArrayList()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(context, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                context as Activity,
                listPermissionsNeeded.toTypedArray(),
                100
            )
            return false
        }
        return true
    }
}
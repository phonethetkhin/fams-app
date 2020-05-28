package com.t3k.mobile.fams.app.utility

import android.R
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.processphoenix.ProcessPhoenix
import com.t3k.mobile.fams.app.model.DeviceModel
import com.t3k.mobile.fams.app.roomdatabase.FamsAppDatabase
import com.t3k.mobile.fams.app.roomdatabase.entities.ServerSettingEntity
import com.t3k.mobile.fams.app.ui.SplashActivity
import com.t3k.mobile.fams.app.viewmodels.ServerSettingViewModel
import java.util.*

fun setToast(context: Context, text: String?, length: Int) {
    Toast.makeText(context, text, length).show()
}

fun setSnackBar(layout: View, text: CharSequence, length: Int) {
    Snackbar.make(layout, text, length).show()
}

fun setFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    backstack: Boolean,
    container: Int
) {

    val transaction: FragmentTransaction =
        fragmentManager.beginTransaction()

    if (!backstack) {
        transaction.replace(container, fragment).commit()
    } else {
        transaction.replace(container, fragment).addToBackStack(null).commit()
    }
}

fun setFragmentbyBundle(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    backstack: Boolean,
    container: Int,
    bundle: Bundle
) {

    val transaction: FragmentTransaction =
        fragmentManager.beginTransaction()
    fragment.arguments = bundle

    if (backstack) {
        transaction.replace(container, fragment).commit()
    } else {
        transaction.replace(container, fragment).addToBackStack(null).commit()
    }


}

fun getColorStateList(enabledColor: Int, disabledColor: Int): ColorStateList {
    return ColorStateList(
        arrayOf(
            intArrayOf(R.attr.state_enabled),
            intArrayOf(-R.attr.state_enabled)
        ),
        intArrayOf(
            enabledColor,
            disabledColor

        )
    )
}

fun setLayoutManagerRecyclerview(
    context: Context,
    recyclerView: RecyclerView,
    orientation: Int,
    setHasFixedSize: Boolean,
    reverseLayout: Boolean
) {
    recyclerView.layoutManager = LinearLayoutManager(context, orientation, reverseLayout)
    recyclerView.setHasFixedSize(setHasFixedSize)
}

fun getExistingServerSetting(
    context: Context,
    protocol: Byte,
    address: String,

    contextPath: String
): Boolean {
    val serverSettingDao =
        FamsAppDatabase.getFAMSDB(context)!!
            .serverSettingDao()
    val serversettingEntity: ServerSettingEntity? =
        serverSettingDao.getExistingServerSetting(
            protocol,
            address,
            contextPath
        )
    return serversettingEntity != null
}
fun getBaseURL(context: Context) : String
{
return getStringPref(context,"fullurl","fullurl","")!!
}
fun initializeServerSetting(
    protocol: Byte,
    address: String,
    addresstype: Byte,
    contextPath: String
): ServerSettingEntity {
    return ServerSettingEntity(0, protocol, addresstype, address, contextPath, 0)
}

fun setExistingServerSetting(
    app:FragmentActivity,
    rdoHttp: RadioButton,
    rdoHttps: RadioButton,
    rdoDomain: RadioButton,
    rdoIPAddress: RadioButton,
    tetAddress: TextInputEditText,
    tetContextpath: TextInputEditText
) {
    val vModel= ViewModelProviders.of(app).get(ServerSettingViewModel::class.java)
     vModel.getServerSetting(1)
    vModel.serverSettingLiveData.observe(app, Observer {
        it?.let {
            val protocol = it.protocol.toString()
            val addressType= it.address_type.toString()
            if(protocol == "0")
            {
            rdoHttp.isChecked = true
            }
            else if(protocol == "1")
            {
                rdoHttps.isChecked = true
            }
            if(addressType == "0")
            {
                rdoDomain.isChecked = true
            }
            else if(addressType == "1")
            {
                rdoIPAddress.isChecked =true
            }
            tetAddress.setText(it.address)
            tetContextpath.setText(it.context_path)
        }
    })



}
@SuppressLint("HardwareIds")
@TargetApi(Build.VERSION_CODES.M)
 fun getDeviceInfo(context: Context,deviceName : String,deviceCode:String) : DeviceModel
{
    AppPermission.checkPermission(context)
    val brand = Build.BRAND.toUpperCase(Locale.ROOT)
    val model = Build.MODEL
    val osVersion = Build.VERSION.RELEASE
    val device = Build.DEVICE
    val wifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wInfo = wifiManager.connectionInfo
    val macWifi = wInfo.macAddress
    val telephonyMgr =
        context.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager
    val telephonyInfo: TelephonyInfo? = TelephonyInfo.getInstance(context)
    AppPermission.checkPermission(context)
    var deviceId1: String? = ""
    var deviceId2:String?=""
    if (telephonyInfo!!.isSIM2Ready && telephonyInfo.isSIM1Ready) {
        deviceId1 = telephonyMgr.getDeviceId(0)
        deviceId2 = telephonyMgr.getDeviceId(1)
    } else {
        deviceId1 = telephonyMgr.deviceId
    }
    val serialNo = Build.SERIAL
    val androidId = Settings.Secure.getString(
        context.applicationContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )
    val fingerPrint = Build.FINGERPRINT
    var imei: String? = ""
    imei = if (Build.VERSION.SDK_INT >= 26) {
        telephonyMgr.imei
    } else {
        telephonyMgr.deviceId
    }

    return DeviceModel(0,deviceCode,deviceName,brand,model,osVersion,device,macWifi,deviceId1,deviceId2,serialNo,androidId,fingerPrint,imei)
}

@SuppressLint("HardwareIds")
@TargetApi(Build.VERSION_CODES.M)
fun getDeviceInfo2(context: Context,deviceName : String) : DeviceModel {
    AppPermission.checkPermission(context)
    val brand = Build.BRAND.toUpperCase(Locale.ROOT)
    val model = Build.MODEL
    val osVersion = Build.VERSION.RELEASE
    val device = Build.DEVICE
    val wifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wInfo = wifiManager.connectionInfo
    val macWifi = wInfo.macAddress
    val telephonyMgr =
        context.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager
    val telephonyInfo: TelephonyInfo? = TelephonyInfo.getInstance(context)
    AppPermission.checkPermission(context)
    var deviceId1: String? = ""
    var deviceId2: String? = ""
    if (telephonyInfo!!.isSIM2Ready && telephonyInfo.isSIM1Ready) {
        deviceId1 = telephonyMgr.getDeviceId(0)
        deviceId2 = telephonyMgr.getDeviceId(1)
    } else {
        deviceId1 = telephonyMgr.deviceId
    }
    val serialNo = Build.SERIAL
    val androidId = Settings.Secure.getString(
        context.applicationContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )
    val fingerPrint = Build.FINGERPRINT
    var imei: String? = ""
    imei = if (Build.VERSION.SDK_INT >= 26) {
        telephonyMgr.imei
    } else {
        telephonyMgr.deviceId
    }

    val dModel = DeviceModel()
    dModel.deviceName = deviceName
    dModel.brand = brand
    dModel.model = model
    dModel.osVersion = osVersion
    dModel.device = device
    dModel.macWifi = macWifi
    dModel.deviceId1 = deviceId1
    dModel.deviceId2 = deviceId2
    dModel.serialNo = serialNo
    dModel.androidId = androidId
    dModel.fingerPrint = fingerPrint
    dModel.imei = imei
    return dModel
}
 fun restart(context: Context)
{
    val nextIntent = Intent(context, SplashActivity::class.java)
    ProcessPhoenix.triggerRebirth(context, nextIntent)
}



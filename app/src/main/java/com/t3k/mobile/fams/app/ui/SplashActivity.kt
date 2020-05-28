@file:Suppress("DEPRECATION")

package com.t3k.mobile.fams.app.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.repositories.CheckDeviceInfoRepo
import com.t3k.mobile.fams.app.utility.*
import com.t3k.mobile.fams.app.viewmodels.ServerSettingViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class SplashActivity : AppCompatActivity() {
    val register: Boolean = false
    val license: Boolean = false


    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) === PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) === PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) === PackageManager.PERMISSION_GRANTED
            ) {
                mainFunction()
            } else if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) === PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                ) {
                    ActivityCompat.requestPermissions(
                        this@SplashActivity,
                        arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ),
                        REQUEST_STORAGE_PERMISSION_CODE
                    )
                }
            } else if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) !== PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) === PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) === PackageManager.PERMISSION_GRANTED
            ) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                    ActivityCompat.requestPermissions(
                        this@SplashActivity,
                        arrayOf(Manifest.permission.READ_PHONE_STATE),
                        REQUEST_PHONE_PERMISSION_CODE
                    )
                }
            } else {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), REQUEST_PERMISSION_CODE
                )
            }
        } else {
            mainFunction()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (getTheme(this)) {
            "Green" -> setTheme(R.style.GreenTheme)
            "Blue" -> setTheme(R.style.BlueTheme)
            "Red" -> setTheme(R.style.RedTheme)
            "Purple" -> setTheme(R.style.Purple)
            else -> setTheme(R.style.GreenTheme)
        }
        setContentView(R.layout.activity_splash)

        val color = getColorsfromtheme(
            this, getTheme(
                this
            )
        )
        pgbLoading.indeterminateDrawable.setColorFilter(
            color,
            PorterDuff.Mode.SRC_IN
        )
        checkPermission()



    }
    private fun mainFunction() {
        val vModel = ViewModelProviders.of(this).get(ServerSettingViewModel::class.java)
        vModel.getAllServerSetting()
        vModel.serverSettingLiveDataList.observe(this, Observer {
            GlobalScope.launch {
                delay(5000L)
                if (it.isEmpty()) {
                    startActivity(Intent(this@SplashActivity, ServerChangeActivity::class.java))
                    finish()
                } else {
                    checkRegister()

                }
            }


        })
    }

    private fun checkRegister() {

        val dInfo = CheckDeviceInfoRepo(this)
        dInfo.checkDeviceInfo()


    }



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    applicationContext,
                    "Application need permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mainFunction()
            }
        } else if (requestCode == REQUEST_PHONE_PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    applicationContext,
                    "Application need read phone permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mainFunction()
            }
        } else if (requestCode == REQUEST_STORAGE_PERMISSION_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    applicationContext,
                    "Application need storage permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mainFunction()
            }
        }
    }

    }


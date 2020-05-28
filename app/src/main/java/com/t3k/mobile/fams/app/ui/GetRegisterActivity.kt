package com.t3k.mobile.fams.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.fragments.getlicense.NoLicenseFragment
import com.t3k.mobile.fams.app.fragments.getregister.DeviceInactiveFragment
import com.t3k.mobile.fams.app.fragments.getregister.NotRegisterFragment
import com.t3k.mobile.fams.app.utility.getTheme
import com.t3k.mobile.fams.app.utility.setFragment


class GetRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (getTheme(this)) {
            "Green" -> setTheme(R.style.GreenTheme)
            "Blue" -> setTheme(R.style.BlueTheme)
            "Red" -> setTheme(R.style.RedTheme)
            "Purple" -> setTheme(R.style.Purple)
            else -> setTheme(R.style.GreenTheme)
        }
        setContentView(R.layout.activity_get_register)
        val status = intent.getIntExtra("status",0)


        if (savedInstanceState == null) {
            when (status) {
                0 -> {
                    setFragment(
                        supportFragmentManager,
                        NotRegisterFragment(),
                        false,
                        R.id.fmlGetRegisterContainer
                    )
                }
                1 -> {
                    setFragment(
                        supportFragmentManager,
                        DeviceInactiveFragment(),
                        false,
                        R.id.fmlGetRegisterContainer
                    )
                }
                else -> {
                    setFragment(
                        supportFragmentManager,
                        NotRegisterFragment(),
                        false,
                        R.id.fmlGetRegisterContainer
                    )
                }
            }
        }


    }
}

package com.t3k.mobile.fams.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.fragments.getlicense.NoExtraLicenseFragment
import com.t3k.mobile.fams.app.fragments.getlicense.NoLicenseFragment
import com.t3k.mobile.fams.app.fragments.getlicense.RequestFailFragment
import com.t3k.mobile.fams.app.fragments.getlicense.RequestPendingFragment
import com.t3k.mobile.fams.app.utility.getTheme
import com.t3k.mobile.fams.app.utility.setFragment

class GetLicenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (getTheme(this)) {
            "Green" -> setTheme(R.style.GreenTheme)
            "Blue" -> setTheme(R.style.BlueTheme)
            "Red" -> setTheme(R.style.RedTheme)
            "Purple" -> setTheme(R.style.Purple)
            else -> setTheme(R.style.GreenTheme)
        }
        setContentView(R.layout.activity_get_license)

        if (savedInstanceState == null) {
            when (intent.getIntExtra("licensestatus", 0)) {
                0 -> {
                    setFragment(
                        supportFragmentManager,
                        NoLicenseFragment(),
                        false,
                        R.id.fmlGetLicenseContainer
                    )
                }
                1 -> {
                    setFragment(
                        supportFragmentManager,
                        RequestPendingFragment(),
                        false,
                        R.id.fmlGetLicenseContainer
                    )
                }
                2 -> {
                    setFragment(
                        supportFragmentManager,
                        NoExtraLicenseFragment(),
                        false,
                        R.id.fmlGetLicenseContainer
                    )
                }
                3 -> {
                    setFragment(
                        supportFragmentManager,
                        RequestFailFragment(),
                        false,
                        R.id.fmlGetLicenseContainer
                    )
                }
                else -> {
                    setFragment(
                        supportFragmentManager,
                        NoLicenseFragment(),
                        false,
                        R.id.fmlGetLicenseContainer
                    )
                }
            }
        }

    }


}

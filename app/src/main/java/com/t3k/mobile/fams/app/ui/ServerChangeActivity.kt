package com.t3k.mobile.fams.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.fragments.getlicense.NoLicenseFragment
import com.t3k.mobile.fams.app.fragments.serverchange.PinFragment
import com.t3k.mobile.fams.app.interfaces.FragmentActivityCommunication
import kotlinx.android.synthetic.main.toolbar.*
import com.t3k.mobile.fams.app.utility.getTheme
import com.t3k.mobile.fams.app.utility.setFragment
import com.t3k.mobile.fams.app.utility.setFragmentbyBundle
import kotlinx.android.synthetic.main.centered_toolbar.*


class ServerChangeActivity : AppCompatActivity(),FragmentActivityCommunication {
    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if(fragment is PinFragment)
        {
            fragment.setonFragmentActivityCommunication(this)

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
        setContentView(R.layout.activity_server_change)
        val tlbToolbar=findViewById<Toolbar>(R.id.tlbToolbar)
        setSupportActionBar(tlbToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
            txtToolbarTitle.text="Server Setting"

        if (savedInstanceState == null) {
            setFragment(supportFragmentManager, PinFragment(), false, R.id.fmlServerChangeContainer)

        }


    }

    override fun setTitleListener(title: String) {

    }

    override fun setNavSelectedState(id: Int) {
    }

    override fun onArticleSelected(position: Int) {
    }

    override fun LocationListener(isLocation: Boolean) {
    }
}

package com.t3k.mobile.fams.app.ui

import android.os.Bundle
import android.os.Handler
import android.os.Process
import android.view.Menu
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.fragments.applicationsetting.ApplicationSettingFragment
import com.t3k.mobile.fams.app.fragments.home.HomeFragment
import com.t3k.mobile.fams.app.fragments.locationlist.LocationListFragment
import com.t3k.mobile.fams.app.fragments.serverchange.PinFragment
import com.t3k.mobile.fams.app.interfaces.FragmentActivityCommunication
import com.t3k.mobile.fams.app.repositories.ServerConnectionCheckRepo
import com.t3k.mobile.fams.app.utility.*
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), FragmentActivityCommunication {
    lateinit var tlbToolbar: Toolbar
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var animation: Animation
    private var isSecond=false


    override fun setTitleListener(title: String) {
        supportActionBar!!.title = title
    }

    override fun onAttachFragment(fragment: Fragment) {
        fragmentAttach(fragment, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBooleanPref(this, "islocation", "location", false)

        animation =
            AnimationUtils.loadAnimation(this, R.anim.load_anim)
        animation.interpolator = AccelerateInterpolator()
        animation.repeatCount = Animation.RELATIVE_TO_PARENT
        animation.duration = 800

        when (getTheme(this)) {
            "Green" -> setTheme(R.style.GreenTheme)
            "Blue" -> setTheme(R.style.BlueTheme)
            "Red" -> setTheme(R.style.RedTheme)
            "Purple" -> setTheme(R.style.Purple)
            else -> setTheme(R.style.GreenTheme)
        }
        setContentView(R.layout.activity_home)
        tlbToolbar = findViewById(R.id.tlbToolbar)
        setSupportActionBar(tlbToolbar)
        supportActionBar!!.title = "Home"
        ngvHome.setCheckedItem(R.id.nav_home)


        val view = ngvHome.getHeaderView(0)
        val color = getColorsfromtheme(this, getTheme(this))
        view.setBackgroundColor(color)


        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            toggle = ActionBarDrawerToggle(
                this, drlHome, tlbToolbar, R.string.open,
                R.string.close
            )
            toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
            if (drlHome != null) {
                drlHome.setDrawerListener(toggle)
                toggle.syncState()
            }
        }


        ngvHome.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.nav_applicationsetting -> {
                    if (drlHome != null) {
                        closeDrawer(drlHome)
                    }
                    setFragment(
                        supportFragmentManager,
                        ApplicationSettingFragment(),
                        false,
                        R.id.fmlHomeContainer
                    )

                    recreate()
                }
                R.id.nav_serverconfig -> {
                    if (drlHome != null) {
                        closeDrawer(drlHome)
                    }
                    val bundle = Bundle()
                    bundle.putString("From", "Home")
                    setFragmentbyBundle(
                        supportFragmentManager,
                        PinFragment(),
                        false,
                        R.id.fmlHomeContainer,
                        bundle
                    )


                    recreate()
                }
                R.id.nav_location -> {
                    if (drlHome != null) {
                        closeDrawer(drlHome)
                    }

                    setFragment(
                        supportFragmentManager,
                        LocationListFragment(),
                        false,
                        R.id.fmlHomeContainer
                    )

                    recreate()

                }
                R.id.nav_exit ->
                {
            AddingDialog()
                }
                else
                -> {
                    if (drlHome != null) {
                        closeDrawer(drlHome)
                    }

                    setFragment(
                        supportFragmentManager,
                        HomeFragment(),
                        false,
                        R.id.fmlHomeContainer
                    )

                    recreate()

                }
            }
            false
        }


    }


    override fun onBackPressed() {
        if (drlHome != null) {
            closeDrawer(drlHome)
            if (!(drlHome.isDrawerOpen(GravityCompat.START))) {
                doubleTapToExit()
            }

        } else {
            doubleTapToExit()


        }


    }



    private fun doubleTapToExit()
   {
       setToast(this,"Press Again to Exit !!!",Toast.LENGTH_SHORT)
       if (isSecond) {
           finishAffinity()
           Process.killProcess(Process.myPid())

   }
       isSecond = true
       Handler().postDelayed({ isSecond = false }, 1000)
   }



    private fun closeDrawer(drlHome: DrawerLayout) {

        if (drlHome.isDrawerOpen(GravityCompat.START)) {
            drlHome.closeDrawer(GravityCompat.START)

        }

    }

    override fun onArticleSelected(position: Int) {
        recreate()
    }

    override fun LocationListener(isLocation: Boolean) {
        setBooleanPref(this, "islocation", "location", isLocation)

    }

    override fun setNavSelectedState(id: Int) {
        val nav = findViewById<NavigationView>(R.id.ngvHome)

        nav.setCheckedItem(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val isLocationFragment = getBooleanPref(this, "islocation", "location", false)
        if (isLocationFragment!!) {
            menuInflater.inflate(R.menu.reload_menu, menu)
            val syncLocation =
                menu!!.findItem(R.id.mitReload).actionView as ImageView
            syncLocation.setImageResource(R.drawable.ic_sync_black_24dp)
            syncLocation.setPadding(20, 20, 20, 20)

            syncLocation.setOnClickListener {
                it.startAnimation(animation)
            }
        }

        return true

    }
    private fun AddingDialog() {
        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure you want to exit?")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes"
        ) { dialog, which ->
            finishAffinity()
            Process.killProcess(Process.myPid())
        }
        builder.setNegativeButton("Cancel"
        ) { _, _ ->  }
        builder.show()

    }






}


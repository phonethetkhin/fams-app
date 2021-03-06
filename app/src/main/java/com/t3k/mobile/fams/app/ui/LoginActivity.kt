package com.t3k.mobile.fams.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.repositories.LoginRepo
import com.t3k.mobile.fams.app.utility.blankValidation
import com.t3k.mobile.fams.app.utility.getColorsfromtheme
import com.t3k.mobile.fams.app.utility.getTheme
import com.t3k.mobile.fams.app.utility.setToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (getTheme(this)) {
            "Green" -> setTheme(R.style.GreenTheme)
            "Blue" -> setTheme(R.style.BlueTheme)
            "Red" -> setTheme(R.style.RedTheme)
            "Purple" -> setTheme(R.style.Purple)
            else -> setTheme(R.style.GreenTheme)
        }
        setContentView(R.layout.activity_login)
        val color = getColorsfromtheme(this, getTheme(this))

        txtTitle.setTextColor(color)
        txtAppName.setTextColor(color)

        imgSetting.setOnClickListener {
            startActivity(Intent(this, ServerChangeActivity::class.java))


        }
        btnRequest.setOnClickListener {
            val loginId = tetLoginID.text.toString()
            val password = tetPassword.text.toString()


            if (blankValidation(loginId, password)) {
               setToast(this,"Please Fill Login ID and Password",Toast.LENGTH_SHORT)
            }
            else{
                mainFunction(loginId, password)
            }


        }
    }
     private fun mainFunction(loginId:String, password : String)
    {
        val loginRepo = LoginRepo(this)
        loginRepo.userLogin(loginId,password)
    }
}

package com.t3k.mobile.fams.app.fragments.getregister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.model.DeviceModel
import com.t3k.mobile.fams.app.repositories.DeviceRegistrationRepo
import com.t3k.mobile.fams.app.ui.GetLicenseActivity
import com.t3k.mobile.fams.app.utility.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import kotlinx.android.synthetic.main.fragment_registration.view.tetDeviceName


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val color= getColorsfromtheme(activity!!.applicationContext, getTheme(activity!!.applicationContext))
        val v = inflater.inflate(R.layout.fragment_registration, container, false)
        v.txtTitle.setTextColor(color)
        v.btnRegister.setOnClickListener {
            if (validationForRegistration(v.tetDeviceName, v.tetLoginID, v.tetPassword)) {
                val deviceRegistrationRepo = DeviceRegistrationRepo(activity!!)
                val dModel = getDeviceInfo2(activity!!,v.tetDeviceName.text.toString())

                val loginId: String = v.tetLoginID.text.toString()
                val password: String = v.tetPassword.text.toString()
                val deviceName =v.tetDeviceName.text.toString()
                setStringPref(activity!!,"devicename","devicename",deviceName)

                deviceRegistrationRepo.registerDeviceInfo(loginId, password, dModel)

            }
        }
        return v
    }

}

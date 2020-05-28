package com.t3k.mobile.fams.app.fragments.getlicense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.model.DeviceModel
import com.t3k.mobile.fams.app.repositories.RequestLicenseRepo
import com.t3k.mobile.fams.app.utility.*
import kotlinx.android.synthetic.main.fragment_license_requesting.view.*


class LicenseRequestingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val color= getColorsfromtheme(activity!!.applicationContext, getTheme(activity!!.applicationContext))

        val v = inflater.inflate(R.layout.fragment_license_requesting, container, false)
        v.txtTitle.setTextColor(color)


        v.btnRequest.setOnClickListener {
            if(validationForLicenseRegistration(v.tetLoginID,v.tetPassword))
            {
                val loginId= v.tetLoginID.text.toString()
                val password = v.tetPassword.text.toString()
                val requestLicenseRepo = RequestLicenseRepo(activity!!)

                val devCode = getStringPref(activity!!,"devicecode","devicecode","")!!
                val devName = getStringPref(activity!!,"devicename","devicename","")!!


                requestLicenseRepo.requestLicense(getDeviceInfo(activity!!,devName,devCode),loginId,password)
            }

        }
        return v

    }

}

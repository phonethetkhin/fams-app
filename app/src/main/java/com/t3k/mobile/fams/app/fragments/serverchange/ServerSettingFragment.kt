@file:Suppress("DEPRECATION")

package com.t3k.mobile.fams.app.fragments.serverchange

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.repositories.ServerConnectionCheckRepo
import com.t3k.mobile.fams.app.utility.*
import com.t3k.mobile.fams.app.viewmodels.ServerSettingViewModel
import kotlinx.android.synthetic.main.fragment_server_setting.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ServerSettingFragment : Fragment() {
    private var protocol: Byte = 0
    private var addressType: Byte = 0
    private var address: String = ""
    private var contextPath: String = ""
    private var protocolName: String = "http://"
    lateinit var pDialog: ProgressDialog
    lateinit var alertDialog: AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_server_setting, container, false)
        pDialog = ProgressDialog(activity!!)

        val color = getColorsfromtheme(
            activity!!,
            getTheme(activity!!.applicationContext)
        )
        v.txtProtocolLabel.setTextColor(color)
        v.txtSettingTypeLabel.setTextColor(color)
        setExistingServerSetting(
            activity!!,
            v.rdoHttp,
            v.rdoHttps,
            v.rdoDomain,
            v.rdoIpAddress,
            v.tetAddress,
            v.tetContextPath
        )

        v.btnSave.setOnClickListener {
        saveFunction(v)

        }

        v.btnCheck.setOnClickListener {

            checkFunction(v)


        }

        return v

    }

    private fun checkFunction(v: View) {
        getValue(v)
        if (blankValidation(address, contextPath)) {
            setToast(
                activity!!,
                "Please Type Address & context path !!!",
                Toast.LENGTH_LONG
            )

        } else {
            if (!(validationforDomainIP(addressType, address))) {
                setToast(
                    activity!!,
                    "This is invalid address !!!. Check your domain name or IP address.",
                    Toast.LENGTH_SHORT
                )
            } else {
                val fullUrl = "$protocolName$address/$contextPath/"
                pDialog.setTitle("Connecting...")
                pDialog.setMessage("Please...Wait...")
                pDialog.isIndeterminate = false
                pDialog.setCancelable(false)
                pDialog.show()
                GlobalScope.launch {

                    delay(2000L)
                    pDialog.dismiss()
                    val serverConnectionCheckRepo = ServerConnectionCheckRepo(activity!!, fullUrl)


                    serverConnectionCheckRepo.checkServerConnection(v.btnSave)


                }
            }

        }
    }

    private fun saveFunction(v : View) {
        getValue(v)

        val fullUrl = "$protocolName$address/$contextPath/"
        setStringPref(activity!!, "fullurl", "fullurl", fullUrl)


        val vServerSettingVM = ViewModelProviders.of(activity!!)
            .get(ServerSettingViewModel::class.java)
        vServerSettingVM.getAllServerSetting()
        vServerSettingVM.serverSettingLiveDataList.observe(activity!!, Observer {
            if (it.isEmpty()) {
                vServerSettingVM.insertServerSetting(
                    initializeServerSetting(
                        protocol,
                        address,
                        addressType,
                        contextPath
                    )
                )
            } else {

                vServerSettingVM.updateServerSetting(
                    initializeServerSetting(
                        protocol,
                        address,
                        addressType,
                        contextPath
                    )
                )
            }
        })

        vServerSettingVM.insertServerSetting(
            initializeServerSetting(
                protocol,
                address,
                addressType,
                contextPath
            )
        )

     showAlertDialog("Success","Server Setting Saved Successfully, application will restart now !!!")


    }

    private fun getValue(v: View) {
        protocol = if (v.rdoHttp.isChecked) {
            0
        } else {
            1
        }
        protocolName = if (v.rdoHttp.isChecked) {
            "http://"
        } else {
            "https://"
        }
        addressType = if (v.rdoDomain.isChecked) {
            0
        } else {
            1
        }
        address = v.tetAddress.text.toString()
        contextPath = v.tetContextPath.text.toString()
    }


    private fun showAlertDialog(title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
        GlobalScope.launch {
            delay(2000L)
            alertDialog.dismiss()
            restart(activity!!)
        }
    }


}

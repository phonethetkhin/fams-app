package com.t3k.mobile.fams.app.fragments.serverchange

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.interfaces.FragmentActivityCommunication
import com.t3k.mobile.fams.app.ui.GetRegisterActivity
import com.t3k.mobile.fams.app.utility.*
import kotlinx.android.synthetic.main.fragment_pin.*
import kotlinx.android.synthetic.main.fragment_pin.view.*

class PinFragment : Fragment() {
    lateinit var fragmentactivity: FragmentActivityCommunication

    fun setonFragmentActivityCommunication(listener: FragmentActivityCommunication) {
        this.fragmentactivity = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentactivity.setNavSelectedState(R.id.nav_serverconfig)
        fragmentactivity.setTitleListener("Server Setting")
        fragmentactivity.LocationListener(false)


        val color = getColorsfromtheme(
            activity!!.applicationContext,
            getTheme(activity!!.applicationContext)
        )

        val v = inflater.inflate(R.layout.fragment_pin, container, false)
        v.pnvVerification.requestFocus()

        v.pnvVerification!!.setTextColor(color)


        val colorStateList = getColorStateList(color,resources.getColor(R.color.black))
        v.pnvVerification.setLineColor(colorStateList)

        v.pnvVerification.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {

                val verificationcode: String = v.pnvVerification.text.toString()
                if (verificationcode.length == 5) {
                    if(verificationcode == VERIFICATION_PIN) {


                                val transaction: FragmentTransaction =
                                    activity?.supportFragmentManager!!.beginTransaction()
                                val containername = arguments?.getString("From")
                                val fragment: ServerSettingFragment = ServerSettingFragment()
                                if (containername != null) {
                                    transaction.replace(R.id.fmlHomeContainer, fragment)
                                        .commit()
                                } else {
                                    transaction.replace(R.id.fmlServerChangeContainer, fragment)
                                        .commit()
                                }
                                v.pnvVerification.text = null
                            }
                    else
                    {
                        setToast(activity!!,"Wrong Verification PIN !!!",Toast.LENGTH_SHORT)
                    }
                        }


                }


        })

        return v
    }

    override fun onResume() {
        super.onResume()
        pnvVerification.requestFocus()
    }

}

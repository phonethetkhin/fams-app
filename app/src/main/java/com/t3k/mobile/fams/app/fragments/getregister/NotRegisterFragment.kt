package com.t3k.mobile.fams.app.fragments.getregister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.utility.getColorsfromtheme
import com.t3k.mobile.fams.app.utility.getTheme
import com.t3k.mobile.fams.app.utility.setFragment
import kotlinx.android.synthetic.main.fragment_not_register.view.*


class NotRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val color= getColorsfromtheme(activity!!.applicationContext, getTheme(activity!!.applicationContext))
        val v = inflater.inflate(R.layout.fragment_not_register, container, false)
        v.txtTitle.setTextColor(color)
        v.btnRegister.setOnClickListener {
            setFragment(activity!!.supportFragmentManager, RegistrationFragment(), false, R.id.fmlGetRegisterContainer)
        }
        return v
    }

}

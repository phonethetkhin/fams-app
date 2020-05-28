package com.t3k.mobile.fams.app.fragments.getlicense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.utility.getColorsfromtheme
import com.t3k.mobile.fams.app.utility.getTheme
import com.t3k.mobile.fams.app.utility.setFragment
import kotlinx.android.synthetic.main.fragment_request_fail.view.*


class RequestFailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val color = getColorsfromtheme(
            activity!!.applicationContext,
            getTheme(activity!!.applicationContext)
        )

        val v = inflater.inflate(R.layout.fragment_request_fail, container, false)
        v.txtTitle.setTextColor(color)
        v.txtContactAdmin.setTextColor(color)

        v.btnTryAgain.setOnClickListener {
            setFragment(
                activity!!.supportFragmentManager,
                NoExtraLicenseFragment(),
                false,
                R.id.fmlGetLicenseContainer
            )
        }
        return v
    }

}

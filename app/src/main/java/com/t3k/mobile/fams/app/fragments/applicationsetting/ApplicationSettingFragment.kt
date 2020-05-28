package com.t3k.mobile.fams.app.fragments.applicationsetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.interfaces.FragmentActivityCommunication
import com.t3k.mobile.fams.app.utility.getColorsfromtheme
import com.t3k.mobile.fams.app.utility.getTheme
import com.t3k.mobile.fams.app.utility.setCheckedtoSelectedColor
import com.t3k.mobile.fams.app.utility.setStringPref
import kotlinx.android.synthetic.main.fragment_application_setting.view.*


class ApplicationSettingFragment : Fragment() {
    lateinit var fragmentactivity: FragmentActivityCommunication

    fun setonFragmentActivityCommunication(listener: FragmentActivityCommunication) {
        this.fragmentactivity = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentactivity.setNavSelectedState(R.id.nav_applicationsetting)
        fragmentactivity.setTitleListener("Application Setting")
        fragmentactivity.LocationListener(false)


        val position: Int = 1
        val theme = getTheme(activity!!.applicationContext)
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_application_setting, container, false)
        setCheckedtoSelectedColor(activity!!.applicationContext, theme, v.imbGreen, v.imbBlue, v.imbRed, v.imbPurple)

        v.txtTitle.setTextColor(getColorsfromtheme(activity!!.applicationContext, theme))



        v.imbGreen.setOnClickListener {
            setCheckedtoSelectedColor(activity!!.applicationContext, "Green", v.imbGreen, v.imbBlue, v.imbRed, v.imbPurple)

            setStringPref(activity!!.applicationContext, "theme", "color_name", "Green")

            fragmentactivity.onArticleSelected(position)


        }


        v.imbBlue.setOnClickListener {
            setCheckedtoSelectedColor(activity!!.applicationContext, "Blue", v.imbGreen, v.imbBlue, v.imbRed, v.imbPurple)

            setStringPref(activity!!.applicationContext, "theme", "color_name", "Blue")


            fragmentactivity.onArticleSelected(position)

        }

        v.imbRed.setOnClickListener {
            setCheckedtoSelectedColor(activity!!.applicationContext, "Red", v.imbGreen, v.imbBlue, v.imbRed, v.imbPurple)

            setStringPref(activity!!.applicationContext, "theme", "color_name", "Red")


            fragmentactivity.onArticleSelected(position)

        }

        v.imbPurple.setOnClickListener {
            setCheckedtoSelectedColor(activity!!.applicationContext, "Purple", v.imbGreen, v.imbBlue, v.imbRed, v.imbPurple)

            setStringPref(activity!!.applicationContext, "theme", "color_name", "Purple")


            fragmentactivity.onArticleSelected(position)

        }
        return v
    }


}

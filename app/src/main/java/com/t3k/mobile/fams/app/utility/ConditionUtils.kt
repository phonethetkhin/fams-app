package com.t3k.mobile.fams.app.utility

import android.content.Context
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.fragments.applicationsetting.ApplicationSettingFragment
import com.t3k.mobile.fams.app.fragments.home.HomeFragment
import com.t3k.mobile.fams.app.fragments.locationlist.LocationListFragment
import com.t3k.mobile.fams.app.fragments.serverchange.PinFragment
import com.t3k.mobile.fams.app.interfaces.FragmentActivityCommunication

fun fragmentAttach(
    fragment: Fragment,
    fragmentActivityCommunication: FragmentActivityCommunication
) {
    when (fragment) {
        is ApplicationSettingFragment -> {
            fragment.setonFragmentActivityCommunication(fragmentActivityCommunication)

        }
        is HomeFragment -> {
            fragment.setonFragmentActivityCommunication(fragmentActivityCommunication)

        }
        is LocationListFragment -> {
            fragment.setonFragmentActivityCommunication(fragmentActivityCommunication)
        }
        is PinFragment -> {
            fragment.setonFragmentActivityCommunication(fragmentActivityCommunication)
        }
    }
}

fun getColorsfromtheme(context: Context, theme: String?): Int {
    val color: Int
    when (theme) {
        "Green" -> color = context.resources.getColor(R.color.colorPrimary)
        "Blue" -> color = context.resources.getColor(R.color.blue)
        "Red" -> color = context.resources.getColor(R.color.red)
        "Purple" -> color = context.resources.getColor(R.color.purple)
        else -> color = context.resources.getColor(R.color.colorPrimary)
    }
    return color
}

fun setCheckedtoSelectedColor(
    context: Context,
    theme: String?,
    green: ImageButton,
    blue: ImageButton,
    red: ImageButton,
    purple: ImageButton
) {
    when (theme) {
        "Green" -> {
            blue.setImageDrawable(null)
            red.setImageDrawable(null)
            purple.setImageDrawable(null)
            green.setImageDrawable(context.resources.getDrawable(R.drawable.ic_check_circle_black_24dp))
        }
        "Blue" -> {
            blue.setImageDrawable(null)
            red.setImageDrawable(null)
            purple.setImageDrawable(null)
            blue.setImageDrawable(context.resources.getDrawable(R.drawable.ic_check_circle_black_24dp))
        }
        "Red" -> {
            blue.setImageDrawable(null)
            red.setImageDrawable(null)
            purple.setImageDrawable(null)
            red.setImageDrawable(context.resources.getDrawable(R.drawable.ic_check_circle_black_24dp))
        }
        "Purple" -> {
            blue.setImageDrawable(null)
            red.setImageDrawable(null)
            purple.setImageDrawable(null)
            purple.setImageDrawable(context.resources.getDrawable(R.drawable.ic_check_circle_black_24dp))
        }
        else -> {
            blue.setImageDrawable(null)
            red.setImageDrawable(null)
            purple.setImageDrawable(null)
            green.setImageDrawable(context.resources.getDrawable(R.drawable.ic_check_circle_black_24dp))
        }

    }
}
    fun get2ChoiceConditions(defaultValue:String,checkValue:String,anotherValue:String) : String{
        return if ("0" == checkValue) {
            defaultValue
        } else {
            anotherValue
        }
    }






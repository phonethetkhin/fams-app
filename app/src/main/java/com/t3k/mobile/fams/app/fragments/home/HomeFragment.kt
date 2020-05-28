package com.t3k.mobile.fams.app.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.interfaces.FragmentActivityCommunication


class HomeFragment : Fragment() {
    lateinit var fragmentactivity: FragmentActivityCommunication

    fun setonFragmentActivityCommunication(listener: FragmentActivityCommunication) {
        this.fragmentactivity = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentactivity.setNavSelectedState(R.id.nav_home)
        fragmentactivity.setTitleListener("Home")
        fragmentactivity.LocationListener(false)

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }

}

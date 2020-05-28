package com.t3k.mobile.fams.app.fragments.locationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.adapter.LocationListAdapter
import com.t3k.mobile.fams.app.interfaces.FragmentActivityCommunication
import com.t3k.mobile.fams.app.model.LocationModel
import com.t3k.mobile.fams.app.utility.setLayoutManagerRecyclerview
import kotlinx.android.synthetic.main.fragment_location_list.*
import kotlinx.android.synthetic.main.fragment_location_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class LocationListFragment : Fragment() {
    var locationlist: MutableList<LocationModel> = mutableListOf<LocationModel>()

    lateinit var fragmentactivity: FragmentActivityCommunication
    lateinit var animation: Animation
    fun setonFragmentActivityCommunication(listener: FragmentActivityCommunication) {
        this.fragmentactivity = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fragmentactivity.setNavSelectedState(R.id.nav_location)
        fragmentactivity.setTitleListener("Location List")
        fragmentactivity.LocationListener(true)
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_location_list, container, false)
        animation = AnimationUtils.loadAnimation(activity, R.anim.load_anim)
        animation.interpolator = AccelerateInterpolator()
        animation.repeatCount = Animation.RELATIVE_TO_PARENT
        animation.duration = 800


        AddedData()


        setLayoutManagerRecyclerview(
            activity!!.applicationContext,
            v.rcvLocationList,
            RecyclerView.VERTICAL,
            true,
            false
        )
        v.rcvLocationList.adapter = LocationListAdapter(activity!!.applicationContext, locationlist)
        return v
    }


    fun AddedData() {
        var locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)
        locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)
        locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)
        locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)
        locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)

        locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)

        locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)

        locationmodel = LocationModel("MHL0000001", "Mottma Holdings")
        locationlist.add(locationmodel)

    }
}

package com.t3k.mobile.fams.app.interfaces

interface FragmentActivityCommunication {
    fun setTitleListener(title: String)
    fun setNavSelectedState(id: Int)
    fun onArticleSelected(position: Int)
    fun LocationListener(isLocation: Boolean)


}
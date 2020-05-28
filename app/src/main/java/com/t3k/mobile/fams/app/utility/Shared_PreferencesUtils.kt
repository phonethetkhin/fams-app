package com.t3k.mobile.fams.app.utility

import android.content.Context

fun getTheme(context: Context): String? {

    val pref = context.getSharedPreferences("theme", Context.MODE_PRIVATE)

    return pref.getString("color_name", "")
}

fun setBooleanPref(context: Context, name: String, key: String, value: Boolean) {
    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putBoolean(key, value)
    editor.apply()
}

fun getBooleanPref(context: Context, name: String, key: String, defaultvalue: Boolean): Boolean? {
    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    return pref.getBoolean(key, defaultvalue)
}

fun setStringPref(context: Context, name: String, key: String, value: String) {
    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putString(key, value)
    editor.apply()
}


fun getStringPref(context: Context, name: String, key: String, defaultvalue: String): String? {
    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
   return pref.getString(key,defaultvalue)
}
fun setIntPref(context: Context, name: String, key: String, value: Int) {
    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putInt(key, value)
    editor.apply()
}


fun getIntPref(context: Context, name: String, key: String, defaultvalue: Int): Int? {
    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    return pref.getInt(key, defaultvalue)
}





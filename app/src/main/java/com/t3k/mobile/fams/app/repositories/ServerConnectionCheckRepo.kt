package com.t3k.mobile.fams.app.repositories

import android.content.Context
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.t3k.mobile.fams.app.retrofit.Retrofitobj
import com.t3k.mobile.fams.app.utility.APP_TYPE
import com.t3k.mobile.fams.app.utility.setBooleanPref
import com.t3k.mobile.fams.app.utility.setStringPref
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerConnectionCheckRepo(val context: Context, baseURL: String?) {
    private val apiService = Retrofitobj(baseURL).apiservice
    lateinit var alertDialog: AlertDialog


    private fun connectionTest(callback: Callback<Void?>) {
        apiService.checkServerConnection(APP_TYPE)!!.enqueue(callback)
    }

    fun checkServerConnection(btnSave : Button) {
        connectionTest(
            object : Callback<Void?> {
                override fun onResponse(
                    call: Call<Void?>,
                    response: Response<Void?>
                ) {
                    if (response.isSuccessful) {
                        connectionSuccessorFailDialog(
                            "Success",
                            "Connection to the server is successful"
                        )
setBooleanPref(context,"serverconnection","serverconnection",true)
                        btnSave.isEnabled = true

                    } else {
                        connectionSuccessorFailDialog(
                            "Error",
                            "Errors occurred when try to connect the server."
                        )


                    }
                }

                override fun onFailure(
                    call: Call<Void?>,
                    t: Throwable
                ) {
                    connectionSuccessorFailDialog("Fail", "Cannot connect to the server !!!")
                }
            })
    }

    private fun connectionSuccessorFailDialog(title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
        GlobalScope.launch {
            delay(2000L)
            alertDialog.dismiss()
        }
    }
}
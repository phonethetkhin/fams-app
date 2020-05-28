package com.t3k.mobile.fams.app.utility

import android.text.TextUtils
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun isIPAddress(ipAddress: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val IP_ADDRESS =
        "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]):[0-9]+$"
    pattern = Pattern.compile(IP_ADDRESS)
    matcher = pattern.matcher(ipAddress)
    return matcher.matches()
}

fun isDomainName(domainName: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val DOMIAN_NAME = "^([a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}$"
    pattern = Pattern.compile(DOMIAN_NAME)
    matcher = pattern.matcher(domainName)
    return matcher.matches()
}

fun blankValidation(address: String, contextPath: String): Boolean {
    return TextUtils.isEmpty(address) || TextUtils.isEmpty(contextPath)
}

fun validationforDomainIP(validationtype: Byte, address: String): Boolean {
    return if (validationtype.toString() == "0") {
        isDomainName(address)
    } else {
        isIPAddress(address)
    }
}


fun validationForRegistration(tetDeviceName:TextInputEditText,tetLoginID:TextInputEditText,tetPassword:TextInputEditText) : Boolean
{
    val deviceName=tetDeviceName.text
    val loginId=tetLoginID.text
    val password=tetPassword.text
    var validation:Boolean = false
    if(deviceName.isNullOrEmpty())
    {
        tetDeviceName.error = "Device Name cannot be empty"
        tetDeviceName.requestFocus()
    }
    else
    {
        if(loginId.isNullOrEmpty())
        {
            tetLoginID.error = "Please Fill Login ID"
            tetLoginID.requestFocus()

        }
        else
        {
            if(password.isNullOrEmpty())
            {
                tetPassword.error ="Password cannot be empty"
                tetPassword.requestFocus()

            }
            else
            {
                if(password.length<=4)
                {
                    tetPassword.error="The passowrd is too short, must have at least 6 character"
                    tetPassword.requestFocus()

                }
                else
                {
                    validation =true
                }
            }
        }
    }
    return validation
}
fun validationForLicenseRegistration(tetLoginID:TextInputEditText,tetPassword:TextInputEditText) : Boolean
{
    val loginId=tetLoginID.text
    val password=tetPassword.text
    var validation:Boolean = false

        if(loginId.isNullOrEmpty()) {
            tetLoginID.error = "Please Fill Login ID"
            tetLoginID.requestFocus()
        }  else
            {
                if (password.isNullOrEmpty()) {
                    tetPassword.error = "Password cannot be empty"
                    tetPassword.requestFocus()

                } else {
                    if (password.length <= 4) {
                        tetPassword.error =
                            "The passowrd is too short, must have at least 4 character"
                        tetPassword.requestFocus()

                    } else {
                        validation = true
                    }
                }
            }




    return validation
}
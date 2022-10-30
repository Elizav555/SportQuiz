package com.elizav.sportquiz.ui.utils

import android.content.Context
import android.os.Build.MANUFACTURER
import android.os.Build.MODEL
import android.telephony.TelephonyManager

object PhoneInfo {
    fun getDeviceName(): String =
        if (MODEL.startsWith(MANUFACTURER, ignoreCase = true)) {
            MODEL
        } else {
            "$MANUFACTURER $MODEL"
        }.uppercase()

    fun isSIMInserted(context: Context): Boolean {
        return TelephonyManager.SIM_STATE_ABSENT != (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simState
    }
}
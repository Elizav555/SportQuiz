package com.elizav.sportquiz.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.elizav.sportquiz.R

object ShowDialog {
    fun showDialog(context: Context, params: DialogParams) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(params.title)
            .setMessage(params.message)
            .setPositiveButton(
                params.submitBtnText,
                params.submitOnClickListener
            )
            .setNegativeButton(R.string.cancel, params.cancelOnClickListener)
            .create()
        dialog.show()
    }
}
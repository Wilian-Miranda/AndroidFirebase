package com.example.androidfirebase1

import android.content.Context
import androidx.appcompat.app.AlertDialog

object Util {
    fun mostraAlert(_context: Context, _message:String){
        val builder = AlertDialog.Builder(_context)
        builder.setTitle("ERROR")
        builder.setMessage(_message)
        builder.setPositiveButton("Aceptar", null)

        val dialog = builder.create()
        dialog.show()
    }
}
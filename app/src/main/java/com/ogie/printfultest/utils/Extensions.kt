package com.ogie.printfultest.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat


fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.toDateTimeString(): String {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date = sdf.parse(this)
        val sdf2 = SimpleDateFormat("EEE, dd MMM, YYYY hh:mm:aa")
        sdf2.format(date!!)
    } catch (t: Throwable) {
        this
    }
}


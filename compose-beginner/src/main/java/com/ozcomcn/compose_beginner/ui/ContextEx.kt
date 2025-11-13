package com.ozcomcn.compose_beginner.ui

import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(
    @StringRes messageRes: Int,
    duration: Int = Toast.LENGTH_SHORT
) = showToast(getString(messageRes), duration)

fun Context.showToast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        Toast.makeText(this, message, duration).show()
    } else {
        mainExecutor.execute { Toast.makeText(this, message, duration).show() }
    }
}
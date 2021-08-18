package com.example.goodevening.superview.view.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showMessageByText(text: String, duration: Int) {
    Snackbar.make(this, text, duration).show()
}
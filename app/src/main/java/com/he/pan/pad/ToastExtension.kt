package com.he.pan.pad

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import androidx.fragment.app.Fragment
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

fun androidx.fragment.app.Fragment.toastCenter(msg: String?) {
    this.activity?.toastCenter(msg)
}

fun Context.toastCenter(msg: String?) {
    if (TextUtils.isEmpty(msg)) {
        return
    }

    val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)



    val textView = TextView(this)
    textView.setTextColor(Color.WHITE)

    val drawable = GradientDrawable()
    drawable.setColor(Color.parseColor("#aa000000"))
    drawable.cornerRadius = 20f

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        textView.background = drawable
    } else {
        textView.setBackgroundDrawable(drawable)
    }

    val tb = 20
    val lr = 20

    textView.setPadding(lr, tb, lr, tb)

    textView.text = msg

    toast.view = textView

    toast.setGravity(Gravity.CENTER, 0, 0)

    toast.show()
}


fun View.click(call: (view: View) -> Unit) {
    // 1秒内只处理一次点击
    RxView.clicks(this)
        .throttleFirst(1000, TimeUnit.MILLISECONDS)
        .subscribe {
            call(this)
        }

}
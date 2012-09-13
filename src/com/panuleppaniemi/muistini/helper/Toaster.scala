package com.panuleppaniemi.muistini.helper

import android.widget.Toast
import android.view.Gravity
import android.content.Context

object Toaster {
  def toast(msg:String, context:Context) = {
    val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0)
    toast.show
  }
}
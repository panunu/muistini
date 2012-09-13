package com.panuleppaniemi.muistini.model

import android.content.ContentValues

class Note(private val value:String) {
  
  def isValid:Boolean = value.length() > 0 
  
  def toContentValues:ContentValues = {
    var values = new ContentValues
    values.put("VALUE", value)
    values
  }
  
}
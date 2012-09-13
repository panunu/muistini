package com.panuleppaniemi.muistini.model

import android.database.Cursor
import android.content.{Context, ContentValues}
import android.database.sqlite._
import android.database.sqlite.SQLiteDatabase.CursorFactory

class Repository(context:Context, name:String, factory:CursorFactory, version:Int) extends SQLiteOpenHelper(context, name, factory, version) {
  
  def all: Cursor = {
    getReadableDatabase rawQuery("SELECT * FROM " + Repository.TABLE_NAME + " ORDER BY _id DESC", null)
  }
  
  def +=(note:Note): Note = {
    val database = getWritableDatabase
    database.insert(Repository.TABLE_NAME, null, note.toContentValues)
    database.close
    note
  }
  
  override def onCreate(database: SQLiteDatabase) = {
    database execSQL
      "CREATE TABLE " + Repository.TABLE_NAME + 
      "( _id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT);"
  }
  
  override def onUpgrade(database: SQLiteDatabase, previousVersion:Int, nextVersion: Int) = {
    database execSQL "DROP TABLE IF EXISTS " + Repository.TABLE_NAME
    onCreate(database)
  }
  
}

object Repository {
  val DATABASE_VERSION = 5
  val DATABASE_NAME = "muistini"
  val TABLE_NAME = "note"
}
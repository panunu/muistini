package com.panuleppaniemi.muistini.model

import android.content.Context

class Service(private val context: Context) {
  val repository = new Repository(context, Repository.DATABASE_NAME, null, Repository.DATABASE_VERSION)  

  def create(value: String):Note = new Note(value)
  def all = repository.all
  def add(note: Note) = if (note.isValid) repository += note else None
}

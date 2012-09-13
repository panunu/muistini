package com.panuleppaniemi.muistini

import android.app.ListActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.util.Log
import android.widget.SimpleCursorAdapter
import com.panuleppaniemi.muistini.model.Service
import com.panuleppaniemi.muistini.model.Repository
import com.panuleppaniemi.muistini.model.Note

class MainActivity extends ListActivity {
  
  var service:Service = null
  var adapter:SimpleCursorAdapter = null
    
  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    
    service = new Service(this)
    adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, service.all, Array("value"), Array(android.R.id.text1))
    
    setContentView(R.layout.activity_main)
    setListAdapter(adapter)
    bind()
  }
  
  private def bind() = {
    findViewById(R.id.button_save).setOnClickListener(new OnClickListener() {
      override def onClick(arg0: View) = {
        // TODO: Test.
        
        val note = service.create(findViewById(R.id.input_text_note).asInstanceOf[EditText].getText.toString)

        service.add(note) match {
          case note: Note => {
            adapter.changeCursor(service.all)
            adapter.notifyDataSetChanged()
            // Notify and clear input.
          }
          case None => // Fail.
        }
      }
    })
  }
}
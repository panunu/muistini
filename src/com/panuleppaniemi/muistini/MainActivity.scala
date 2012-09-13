package com.panuleppaniemi.muistini

import android.app.ListActivity
import android.os.Bundle
import android.view.{Menu, View, Gravity}
import android.view.View.OnClickListener
import android.widget.{ArrayAdapter, EditText, SimpleCursorAdapter, Toast}
import com.panuleppaniemi.muistini.model._

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
        
        val input = findViewById(R.id.input_text_note).asInstanceOf[EditText]
        val note = service.create(input.getText.toString)

        service.add(note) match {
          case note: Note => {
            adapter.changeCursor(service.all)
            adapter.notifyDataSetChanged()
            
            val toast = Toast.makeText(getApplicationContext(), "Note saved!", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0)
            toast.show
            
            input.setText("")
          }
          case None => // Fail.
        }
      }
    })
  }
}
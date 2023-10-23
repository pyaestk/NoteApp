package com.example.crudapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.crudapp.R

class NoteCreateActivity : AppCompatActivity() {

    lateinit var editTextTitle: EditText
    lateinit var editTextDes: EditText
    lateinit var editTextMultiNote: EditText
    lateinit var noteCancel: ImageView
    lateinit var noteSave: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_create)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDes = findViewById(R.id.editTextDes)
        editTextMultiNote = findViewById(R.id.editTextMultiLineNotes)
        noteCancel = findViewById(R.id.noteCloseButton)
        noteSave = findViewById(R.id.noteSaveButton)

        noteSave.setOnClickListener {
            if (editTextTitle.text.isEmpty() && editTextDes.text.isEmpty() && editTextMultiNote.text.isEmpty()){
                finish()
            } else {
                saveNote()
            }

        }

        noteCancel.setOnClickListener {
            finish()
        }
    }

    fun saveNote() {

        val noteTitle: String = editTextTitle.text.toString()
        val noteDes: String = editTextDes.text.toString()
        val noteMultiNotes: String = editTextMultiNote.text.toString()
        
        val intent = Intent()
        intent.putExtra("title", noteTitle)
        intent.putExtra("des", noteDes)
        intent.putExtra("multinote", noteMultiNotes)
        // send result and data to the mainActivity
        setResult(RESULT_OK, intent)
        finish()
    }
}
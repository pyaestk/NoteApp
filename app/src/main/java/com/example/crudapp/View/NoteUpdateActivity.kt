package com.example.crudapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.crudapp.R

class NoteUpdateActivity : AppCompatActivity() {

    lateinit var editTextTitle: TextView
    lateinit var editTextDes: TextView
    lateinit var editTextMultiNotes: TextView

    lateinit var updateSaveButton: ImageView
    lateinit var updateCancelButton: ImageView

    var currentId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_update)

        editTextTitle = findViewById(R.id.editTextTitleUpdate)
        editTextDes = findViewById(R.id.editTextDesUpdate)
        editTextMultiNotes = findViewById(R.id.editTextNotesUpdate)

        updateSaveButton = findViewById(R.id.noteSaveButtonUpdate)
        updateCancelButton = findViewById(R.id.noteCloseButtonUpdate)

        getAndSet()

        updateCancelButton.setOnClickListener {
            finish()
        }

        updateSaveButton.setOnClickListener {
            updateNote()
        }
    }

    fun updateNote() {

        val updatedTitle = editTextTitle.text.toString()
        val updatedDes = editTextDes.text.toString()
        val updatedMultiNotes = editTextMultiNotes.text.toString()

        val intent = Intent()
        intent.putExtra("updatedTitle", updatedTitle)
        intent.putExtra("updatedDes", updatedDes)
        intent.putExtra("updatedMultiNotes", updatedMultiNotes)

        if (currentId != 1) {

            intent.putExtra("noteId", currentId)
            setResult(RESULT_OK, intent)
            finish()

        }

    }

    fun getAndSet() {

        val currentTitle = intent.getStringExtra("currentTitle")
        val currentDes = intent.getStringExtra("currentDes")
        val currentMultiNotes = intent.getStringExtra("currentMultiNotes")
        currentId = intent.getIntExtra("currentId", -1)

        editTextTitle.text = currentTitle
        editTextDes.text = currentDes
        editTextMultiNotes.text = currentMultiNotes

    }
}
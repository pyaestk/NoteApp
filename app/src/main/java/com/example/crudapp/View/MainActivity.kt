package com.example.crudapp.View

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.Adapter.NoteAdapter
import com.example.crudapp.NoteApplication
import com.example.crudapp.R
import com.example.crudapp.Repository.NoteRepository
import com.example.crudapp.ViewModel.NoteViewModel
import com.example.crudapp.ViewModel.NoteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Note App"

        recyclerView = findViewById(R.id.noteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        noteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

        noteViewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        //update UI
        noteViewModel.myAllNotes.observe(this, Observer { notes ->

            noteAdapter.setNote(notes)
            
        }) //live data method

        val addNoteButton: FloatingActionButton = findViewById(R.id.addNoteButton)
        addNoteButton.setOnClickListener {

            val intent = Intent(this, NoteCreateActivity::class.java)
            startActivity(intent)

        }


    }
    
}
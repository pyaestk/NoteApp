package com.example.crudapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    }
}
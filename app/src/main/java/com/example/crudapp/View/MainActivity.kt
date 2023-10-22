package com.example.crudapp.View

import android.content.ClipData.Item
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.Adapter.NoteAdapter
import com.example.crudapp.Model.Note
import com.example.crudapp.NoteApplication
import com.example.crudapp.R
import com.example.crudapp.ViewModel.NoteViewModel
import com.example.crudapp.ViewModel.NoteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter

    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for recycler view
        recyclerView = findViewById(R.id.noteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter

        registerActivityResultLauncher()

        //for viewModel
        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

        noteViewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        //update UI
        noteViewModel.myAllNotes.observe(this, Observer { notes ->

            noteAdapter.setNote(notes)
            
        }) //live data method


        //for deleting notes
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0
            , ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //viewHolder.adapterPosition //position of notes
                noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))
                Toast.makeText(applicationContext, "Note has been deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(recyclerView) //for deleting

        val addNoteButton: FloatingActionButton = findViewById(R.id.addNoteButton)
        addNoteButton.setOnClickListener {

            val intent = Intent(this, NoteCreateActivity::class.java)
            //startActivity(intent)
            addActivityResultLauncher.launch(intent)

        }

        val deleteAllNotes: ImageView = findViewById(R.id.deleteAllNotes)
        deleteAllNotes.setOnClickListener {
            showDialogMessage()
            
        }


    }

    fun showDialogMessage() {
        val dialogMessage = AlertDialog.Builder(this)
            .setTitle("Delete All Notes")
            .setMessage("Do you want to delete all notes?")
        
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener { dialog, i ->
            dialog.cancel()
        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, i ->
            noteViewModel.deleteAllNotes()
            Toast.makeText(applicationContext, "All notes have been deleted", Toast.LENGTH_SHORT).show()
        })


        dialogMessage.create().show()

    }


    fun registerActivityResultLauncher() {
       addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
           , ActivityResultCallback { result ->

               val resultCode = result.resultCode
               val data = result.data

               if(resultCode == RESULT_OK && data != null) {

                   val noteTitle: String = data.getStringExtra("title").toString()
                   val noteDes: String = data.getStringExtra("des").toString()
                   val noteMultiNotes: String = data.getStringExtra("multinote").toString()

                   val note = Note(noteTitle, noteDes, noteMultiNotes)

                   //add to database
                   noteViewModel.insert(note)
               }

           })
    }
    
}
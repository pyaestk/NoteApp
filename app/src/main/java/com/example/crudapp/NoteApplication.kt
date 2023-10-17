package com.example.crudapp

import android.app.Application
import com.example.crudapp.Repository.NoteRepository
import com.example.crudapp.Room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    //get NoteDatabase
    val database by lazy {
        NoteDatabase.getDatabase(this, applicationScope)
    }
    //get NoteRepo
    val repository by lazy {
        NoteRepository(database.getNoteDao())
    }
}
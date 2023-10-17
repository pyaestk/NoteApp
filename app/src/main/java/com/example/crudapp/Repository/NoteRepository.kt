package com.example.crudapp.Repository

import androidx.annotation.WorkerThread
import com.example.crudapp.Model.Note
import com.example.crudapp.Room.Dao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: Dao) {

    val myAllNote: Flow<List<Note>> = noteDao.getAllNotes()
    
    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    @WorkerThread
    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    @WorkerThread
    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    @WorkerThread
    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

}
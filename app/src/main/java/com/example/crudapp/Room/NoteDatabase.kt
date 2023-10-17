package com.example.crudapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.crudapp.Model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao(): Dao //Dao is return type object

    //Singleton
    companion object {
        @Volatile
        private var INSTANCE : NoteDatabase ?= null

        fun getDatabase(context: Context, scope: CoroutineScope) : NoteDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(context.applicationContext,
                    NoteDatabase::class.java, "note_data")
                    .addCallback(NoteDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance

            }
        }

    }


    //adding database by default
    private class NoteDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                //database.getNoteDao().insert(Note("t", "d"))

                //coroutine
                scope.launch {
                    val noteDao = database.getNoteDao()
                    noteDao.insert(Note("Title 1", "Description"))
                    noteDao.insert(Note("Title 2", "Description"))
                    noteDao.insert(Note("Title 3", "Description"))
                }
            }
        }

    }
}
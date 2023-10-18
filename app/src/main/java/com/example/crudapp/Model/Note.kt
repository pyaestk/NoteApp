package com.example.crudapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(val title: String, val des: String, val mutiNots: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
    
}

package com.example.crudapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudapp.Repository.NoteRepository
import java.lang.IllegalArgumentException

class NoteViewModelFactory(private var repository: NoteRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //checks if the modelClass is or is a subclass of NoteViewModel.
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown view model")
        }

    }

}
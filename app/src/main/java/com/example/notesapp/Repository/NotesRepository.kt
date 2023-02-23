package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.Model.Notes

class NotesRepository(val dao: NotesDao) {
    fun allNotes(): LiveData<List<Notes>> {
        return dao.getAllNotes()
    }

    fun highNotes(): LiveData<List<Notes>> = dao.getHighNotes()
    fun mediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()
    fun lowNotes(): LiveData<List<Notes>> = dao.getLowNotes()

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}
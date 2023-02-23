package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)

abstract class NotesDB : RoomDatabase() {

    abstract fun myNoteDao(): NotesDao

    companion object {

        var INSTANCE: NotesDB? = null
        fun getDatabaseInstance(context: Context): NotesDB {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDB::class.java, "Notes")
                        .allowMainThreadQueries().build()

                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}
package com.example.notesapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
class Notes(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val title: String,
    var subtitle: String,
    var notes: String,
    var date: String,
    var priority: String


) : Parcelable
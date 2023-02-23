package com.example.notesapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNotesBinding
import com.example.notesapp.ui.Fragment.HomeFragmentDirections

class NotesAdapter(val requiredContext:Context, val notesList:List<Notes>):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater
            .from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubtitle.text = data.subtitle
        holder.binding.notesDate.text = data.date

        when (data.priority)
        {
            "1" -> {
                holder.binding.notesPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.notesPriority.setBackgroundResource(R.drawable.yello_dot)
            }
            "3" -> {
                holder.binding.notesPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditsFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

}
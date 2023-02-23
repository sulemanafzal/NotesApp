package com.example.notesapp.ui.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import java.text.DateFormat
import java.util.*

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority:String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding =FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.pgreen.setImageResource(R.drawable.baseline_done_24)


        binding.pgreen.setOnClickListener {
        priority = "1"
           binding.pgreen.setImageResource(R.drawable.baseline_done_24)
            binding.pred.setImageResource(0)
            binding.pyello.setImageResource(0)

        }
        binding.pyello.setOnClickListener {
            priority = "2"
            binding.pyello.setImageResource(R.drawable.baseline_done_24)
            binding.pred.setImageResource(0)
            binding.pgreen.setImageResource(0)
        }

        binding.pred.setOnClickListener {
            priority = "2"
            binding.pred.setImageResource(R.drawable.baseline_done_24)
            binding.pyello.setImageResource(0)
            binding.pgreen.setImageResource(0)
        }

        binding.btnSave.setOnClickListener {

            createNotes(it)
        }

        return binding.root


    }

    private fun createNotes(it: View?) {

       val title = binding.titleTxt.text.toString()
       val subtitle = binding.subtitleTxt.text.toString()
       val notes = binding.notesTxt.text.toString()
        val d = Date()
        val s: CharSequence = android.text.format.DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Notes(
            null,
            title=title,
            subtitle = subtitle,
            notes = notes,
            date = s.toString(),
        priority
        )

        viewModel.addNotes(data)

       Toast.makeText(requireContext(), "created", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)

    }
}
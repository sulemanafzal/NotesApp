package com.example.notesapp.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentEditsBinding

class EditsFragment : Fragment() {

    val notes by navArgs<EditsFragmentArgs>()
    lateinit var binding: FragmentEditsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditsBinding.inflate(layoutInflater, container, false)

        binding.titleTxt.setText(notes.data.title)
        binding.subtitleTxt.setText(notes.data.subtitle)
        binding.notesTxt.setText(notes.data.notes)

        return binding.root


    }
}
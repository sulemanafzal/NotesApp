package com.example.notesapp.ui.Fragment

import android.nfc.Tag
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewMode: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewMode.getNotes().observe(viewLifecycleOwner) { notesList ->

            binding.recAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recAddNotes.adapter = NotesAdapter(requireContext(), notesList)

        }
        binding.filterAll.setOnClickListener {

            viewMode.getNotes().observe(viewLifecycleOwner) { notesList ->

                binding.recAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recAddNotes.adapter = NotesAdapter(requireContext(), notesList)

            }
        }
        binding.filterHigh.setOnClickListener {

            viewMode.gethighNotes().observe(viewLifecycleOwner) { notesList ->

                binding.recAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }
        }
        binding.filterMedium.setOnClickListener {

            viewMode.getmediumNotes().observe(viewLifecycleOwner) { notesList ->
                binding.recAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }
        }
        binding.filterLow.setOnClickListener {
            viewMode.getlowNotes().observe(viewLifecycleOwner) { notesList ->
                binding.recAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }
        }
        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }
}
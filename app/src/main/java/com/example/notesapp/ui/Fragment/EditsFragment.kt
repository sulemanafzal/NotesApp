package com.example.notesapp.ui.Fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditsFragment : Fragment() {
    var priority:String = "1"
    val oldnotes by navArgs<EditsFragmentArgs>()
    lateinit var binding: FragmentEditsBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditsBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.titleTxt.setText(oldnotes.data.title)
        binding.subtitleTxt.setText(oldnotes.data.subtitle)
        binding.notesTxt.setText(oldnotes.data.notes)

        when (oldnotes.data.priority)
        {
            "1" -> {
                priority = "1"
                binding.pgreen.setImageResource(R.drawable.baseline_done_24)
                binding.pred.setImageResource(0)
                binding.pyello.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pyello.setImageResource(R.drawable.baseline_done_24)
                binding.pred.setImageResource(0)
                binding.pgreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pred.setImageResource(R.drawable.baseline_done_24)
                binding.pyello.setImageResource(0)
                binding.pgreen.setImageResource(0)
            }
        }


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
            priority = "3"
            binding.pred.setImageResource(R.drawable.baseline_done_24)
            binding.pyello.setImageResource(0)
            binding.pgreen.setImageResource(0)
        }

        binding.btnEdit.setOnClickListener {
            updateNotes(it)
        }

        return binding.root


    }

    private fun updateNotes(it: View?) {

        val title = binding.titleTxt.text.toString()
        val subtitle = binding.subtitleTxt.text.toString()
        val notes = binding.notesTxt.text.toString()
        val d = Date()
        val s: CharSequence = android.text.format.DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Notes(
            oldnotes.data.id,
            title=title,
            subtitle = subtitle,
            notes = notes,
            date = s.toString(),
            priority
        )

        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editsFragment_to_homeFragment)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete)
        {
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialoge_delete)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener {
                viewModel.deleteNotes(oldnotes.data.id !!)
                bottomSheet.dismiss()
            }

            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)

    }
}
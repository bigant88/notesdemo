package com.example.notesdemo.ui.addedit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesdemo.R
import com.example.notesdemo.databinding.FragmentAddEditBinding
import com.example.notesdemo.util.setActionBarTitle

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddEditNoteFragment : Fragment() {

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AddEditNoteViewModel>()
    private val args: AddEditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false).apply {
            this.viewModel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = args.note
        if(note != null){
            viewModel.startEdit(note)
            binding.noteTitle.setText(note.title)
            binding.noteContent.setText(note.content)
            setActionBarTitle(getString(R.string.edit_note))
        }else {
            setActionBarTitle(getString(R.string.create_fragment_label))
        }
        binding.saveNoteFab.setOnClickListener {
            if(note != null){
                viewModel.updateNote(binding.noteTitle.text.toString(), binding.noteContent.text.toString())
            }else {
                viewModel.createNote(binding.noteTitle.text.toString(), binding.noteContent.text.toString())
            }
        }
        viewModel.isUpdateCompleted.observe(viewLifecycleOwner) { isUpdateCompleted ->
            if(isUpdateCompleted)
                findNavController().navigate(AddEditNoteFragmentDirections.actionAddOrEditToList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
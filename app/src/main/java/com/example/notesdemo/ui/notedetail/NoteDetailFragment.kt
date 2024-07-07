package com.example.notesdemo.ui.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesdemo.databinding.FragmentNoteDetailBinding
import com.example.notesdemo.ui.addedit.AddEditNoteFragmentDirections

class NoteDetailFragment : Fragment() {
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!
    private val args: NoteDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<NoteDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false).apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        binding.editButton.setOnClickListener {
            findNavController().navigate(NoteDetailFragmentDirections.actionDetailToEdit(viewModel.note))
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteNote()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.start(args.note)
        viewModel.isDeleteCompleted.observe(viewLifecycleOwner) { isDeleteCompleted ->
            if(isDeleteCompleted)
                findNavController().navigate(NoteDetailFragmentDirections.actionDetailToList())
        }
    }

}
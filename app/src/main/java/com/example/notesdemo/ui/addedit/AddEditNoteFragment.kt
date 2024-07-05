package com.example.notesdemo.ui.addedit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notesdemo.R
import com.example.notesdemo.databinding.FragmentAddEditBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddEditNoteFragment : Fragment() {

    private var _binding: FragmentAddEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_add_or_edit_to_list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
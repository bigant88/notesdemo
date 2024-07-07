package com.example.notesdemo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.R
import com.example.notesdemo.databinding.FragmentListNotesBinding
import com.example.notesdemo.databinding.NoteItemBinding
import com.example.notesdemo.domainmodels.Note

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListNotesFragment : Fragment() {
    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ListNotesViewModel>()
    private var viewModelAdapter: NoteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false).apply {
            this.viewModel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        viewModelAdapter = NoteAdapter(NoteClick {
            findNavController().navigate(ListNotesFragmentDirections.actionListToDetail(it))
        })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_add_or_edit)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            notes?.apply {
                viewModelAdapter?.notes = notes
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class NoteClick(val clickNote: (Note) -> Unit) {
    /**
     * Called when a Note is clicked
     *
     * @param note the Note that was clicked
     */
    fun onClick(note: Note) = clickNote(note)
}


/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class NoteAdapter(val callback: NoteClick) : RecyclerView.Adapter<NoteViewHolder>() {

    /**
     * The Notes that our Adapter will show
     */
    var notes: List<Note> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val withDataBinding: NoteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NoteViewHolder.LAYOUT,
            parent,
            false
        )
        return NoteViewHolder(withDataBinding)
    }

    override fun getItemCount() = notes.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.note = notes[position]
            it.noteCallback = callback
        }
    }
}

/**
 * ViewHolder for Note items. All work is done by data binding.
 */
class NoteViewHolder(val viewDataBinding: NoteItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.note_item
    }
}
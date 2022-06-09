package com.example.android.sportsteamtracker.overview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android.sportsteamtracker.databinding.FragmentOverviewBinding

private const val TAG = "OverviewFragment"

/**
 * This fragment shows the the status of the Mars photos web services transaction.
 */
class OverviewFragment : Fragment() {

    private val model: OverviewViewModel by viewModels()

    private var _binding : FragmentOverviewBinding? = null
    private val binding get() = _binding!!


    private fun hideKeyBoard(){
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    private fun sendEventQuery(){
        model.getEventInfo(binding.inputEventName.text.toString())
        hideKeyBoard()
    }

//    private fun sendTeamQuery(){
//        model.getTeamInfo(binding.inputTeamName.text.toString())
//        hideKeyBoard()
//    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    /**
     * Sets layout's lifecycle owner to the OverviewFragment to enable Data Binding to observe LiveData
     * and sets up the RecyclerView with an adapter.
     * and sets button click listener
     */
    private fun initializeBindings(){
        binding.apply {
            lifecycleOwner = this@OverviewFragment
            viewModel = model
            recyclerView.adapter = EventListAdapter()
            recyclerView.addItemDecoration(DividerItemDecoration(this@OverviewFragment.context, DividerItemDecoration.VERTICAL))
            buttonEvent.setOnClickListener { sendEventQuery() }
        }
    }
    private fun initializeViews() {}
    private fun initViewModelProperties(){}
    private fun initializeClient(){}
    private fun initializeObservers(){}
}

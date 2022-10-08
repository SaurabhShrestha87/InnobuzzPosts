package com.innobuzz.postapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innobuzz.postapp.R
import com.innobuzz.postapp.adapter.RecyclerViewAdapter
import com.innobuzz.postapp.databinding.FragmentFirstBinding
import com.innobuzz.postapp.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private val sharedViewModel: MainActivityViewModel by activityViewModels()

    private var binding: FragmentFirstBinding? = null

    private val recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentBinding = FragmentFirstBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get user data to edit
        binding?.viewModel = sharedViewModel
        binding?.firstFragment  = this
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = recyclerViewAdapter
        initMainViewModel()
    }

    private fun initMainViewModel() {
        sharedViewModel.getAllRepositoryList().observe(viewLifecycleOwner) {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        }
        sharedViewModel.makeApiCall()
        recyclerViewAdapter.onDataUserClickListener = {
            sharedViewModel.setQuantity(it.id)
            findNavController().navigate(R.id.action_first_to_second)
        }
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
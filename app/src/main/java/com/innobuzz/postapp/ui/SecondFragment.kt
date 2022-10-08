package com.innobuzz.postapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.innobuzz.postapp.R
import com.innobuzz.postapp.databinding.FragmentSecondBinding
import com.innobuzz.postapp.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var dataUserId: Int? = -1
    private var binding: FragmentSecondBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentBinding = FragmentSecondBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataUserId = sharedViewModel.selectedPostId.value
        binding?.viewModel = sharedViewModel
        binding?.secondFragment = this
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    /**
     *  | something like able to edit the data in db  |
     */
    fun edit() {
        sharedViewModel.showToastMessage("In Progress")
    }

    /**
     * Cancel the selected item  and start over.
     */
    fun popFragment() {
        // Reset order in view model
        sharedViewModel.resetSelection()
        // Navigate back to the [FirstFragment] to start over
        findNavController().navigate(R.id.action_pop_second)
    }

    fun share() {
        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, "Shared Data")
            .putExtra(Intent.EXTRA_TEXT, sharedViewModel.selectedPostId.value.toString())

        // Check if there's an app that can handle this intent before launching it
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            startActivity(intent)
        }
    }

}
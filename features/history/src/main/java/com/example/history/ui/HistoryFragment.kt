package com.example.history.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.history.R
import com.example.history.databinding.FragmentHistoryBinding
import com.example.history.presentation.HistoryState
import com.example.history.presentation.HistoryViewModel
import com.example.history.ui.adapter.BinsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels()

    private val binsAdapter = BinsAdapter(
        onItemClicked = {
            println("onItemClicked " + it)
            viewModel.openBinInfo(it)
        },
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistoryBinding.bind(view)
        initRV()
        viewModel.state.onEach(::render).launchIn(viewModel.viewModelScope)
        viewModel.loadHistory()
    }

    private fun initRV() {
        binding.rvHistory.adapter = binsAdapter
    }

    private fun render(state: HistoryState) {
        if (state is HistoryState.Loaded) {
            binsAdapter.bins = state.bins
        }
    }
}
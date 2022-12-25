package com.example.home.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.presentation.HomeAction
import com.example.home.presentation.HomeState
import com.example.home.presentation.HomeViewModel
import com.example.home.ui.viewPagerAdapter.ViewPagerAdapter
import com.example.remote.domain.entity.BinInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val viewPagerAdapter = ViewPagerAdapter(
        onSearchBtnClicked = {
            viewModel.loadInfoByBin(it)
        },
        onHistoryBtnClicked = {
            viewModel.goToHistory()
        },
        onHyperClicked = {
            goToHyperLink(it)
        }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                if (binding.vpMain.currentItem > 0) {
                    viewModel.goToPreviousPage()
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        initVP()
        initObservers()
        viewModel.checkBinInfo()
    }

    private fun initVP() {
        binding.vpMain.adapter = viewPagerAdapter
        binding.vpMain.isUserInputEnabled = false
    }

    private fun initObservers() {
        viewModel.state.onEach(::render).launchIn(viewModel.viewModelScope)
        viewModel.viewModelScope.launch {
            viewModel.actions.collect {
                handleAction(it)
            }
        }
    }

    private fun render(state: HomeState) {
        if (state is HomeState.Loaded) {
            viewPagerAdapter.binInfo = state.binInfo
            binding.vpMain.post {
                binding.vpMain.setCurrentItem(state.currentPage, true)
            }
        }
    }

    private fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.ShowError   -> {
                val message = SpannableString(action.message)
                message.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    0,
                    message.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                context?.let {
                    AlertDialog
                        .Builder(it)
                        .setTitle(getString(R.string.error_dialog_title))
                        .setMessage(message)
                        .setNeutralButton("Ok") { _, _ -> }
                        .show()
                }
            }
        }
    }

    private fun goToHyperLink(uri: Uri) {
        val packageManager = requireActivity().packageManager
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                context,
                getString(R.string.warning_no_activity_to_handle),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {

        private const val BIN_INFO = "binInfo"

        fun createBundle(binInfo: BinInfo) =
            bundleOf(
                BIN_INFO to binInfo
            )
    }
}
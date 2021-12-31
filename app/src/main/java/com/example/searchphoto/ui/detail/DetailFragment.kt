package com.example.searchphoto.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchphoto.ui.main.MainViewModel
import com.example.searchphoto.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainViewModel.getData()
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    HomeScreen(context,mainViewModel)
                }
            }
        }
    }
}
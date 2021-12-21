package com.example.searchphoto.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.searchphoto.R
import com.example.searchphoto.databinding.FragmentMainBinding
import com.example.searchphoto.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import data.DataState
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val mainViewModel: MainViewModel by viewModels()


    override fun init(view: View, savedInstanceState: Bundle?) {

        binding.buttonTest.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment)
        }

        val adapter = MainAdapter()
        binding.recycler.apply {
            this.adapter = adapter
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.picturesState.collect {
                when (it) {
                    DataState.Empty -> Unit
                    is DataState.Error -> {
                        binding.progress.visibility = View.GONE
                        Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    DataState.Loading -> binding.progress.visibility = View.VISIBLE
                    is DataState.Success -> {
                        adapter.submitList(it.data)
                        binding.progress.visibility = View.GONE
                    }
                }

            }

        }
        mainViewModel.getData()
    }

}
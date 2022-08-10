package com.example.searchphoto.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.searchphoto.databinding.FragmentMainBinding
import com.example.searchphoto.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import data.DataState

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun init(view: View, savedInstanceState: Bundle?) {

        val adapter = MainAdapter()
        binding.recycler.apply {
            this.adapter = adapter
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.picturesStateFlow.collect {
                when (it) {
                    DataState.Empty -> Unit
                    is DataState.Error -> {
                        binding.progress.visibility = View.GONE
                        Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        Log.e("fuck", it.exception.toString())
                    }
                    DataState.Loading -> binding.progress.visibility = View.VISIBLE
                    is DataState.Success -> {
                        adapter.submitList(it.data.photos)
                        binding.progress.visibility = View.GONE
                    }
                }

            }

        }
        binding.buttonSearch.setOnClickListener {
            binding.searchEdit.text.toString().let {
                if (it.isNotEmpty()) {
                    mainViewModel.getData(it)
                } else {
                    Toast.makeText(context, " fill the search bat first", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}
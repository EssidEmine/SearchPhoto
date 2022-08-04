package com.example.searchphoto.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import data.DataState
import kotlinx.coroutines.flow.*
import main.PhotosItem
import repository.SearchPhotosRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotosRepo: SearchPhotosRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _getPicturesState: MutableStateFlow<DataState<PhotosItem>> =
        MutableStateFlow(DataState.Empty)
    val getPicturesState: StateFlow<DataState<PhotosItem>>
        get() = _getPicturesState


    fun getData(search: String) {
        getPhotosRepo.getPhotosSearch(search).onEach { element ->
            _getPicturesState.value = element
        }.flowOn(dispatcherProvider.io)
            .launchIn(viewModelScope)
    }
}
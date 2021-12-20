package com.example.searchphoto.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import main.PhotosItem
import usecase.GetPhotosUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getPhotos: GetPhotosUseCase) : ViewModel() {

    private val _photosState: MutableStateFlow<DataState<List<PhotosItem>>> = MutableStateFlow(DataState.Empty)
    val picturesState: StateFlow<DataState<List<PhotosItem>>>
        get() = _photosState

    init {
        getPhotos.getPhotosSearch().onEach { element ->
            _photosState.value = element
        }.launchIn(viewModelScope)
    }
}
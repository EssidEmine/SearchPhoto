package com.example.searchphoto.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import data.DataState
import kotlinx.coroutines.flow.*
import main.PhotosItem
import usecase.GetPhotosUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotos: GetPhotosUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _photosState: MutableStateFlow<DataState<List<PhotosItem>>> =
        MutableStateFlow(DataState.Empty)
    val picturesState: StateFlow<DataState<List<PhotosItem>>>
        get() = _photosState


    fun getData() {
        getPhotos.getPhotosSearch().onEach { element ->
            _photosState.value = element
        }.flowOn(dispatcherProvider.io)
            .launchIn(viewModelScope)
    }
}
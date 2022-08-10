package com.example.searchphoto.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import data.DataState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import main.PhotosItem
import repository.SearchPhotosRepository
import javax.inject.Inject

@OptIn(FlowPreview::class) //ALERT flatMapConcat est encore en preview d'ou cette annotation
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotosRepo: SearchPhotosRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val searchMutableSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)
    val picturesStateFlow: Flow<DataState<PhotosItem>> =
        searchMutableSharedFlow.distinctUntilChanged()
            .flatMapConcat { search ->
                getPhotosRepo.getPhotosSearch(search)
            }.flowOn(dispatcherProvider.io)

    fun getData(search: String) {
        searchMutableSharedFlow.tryEmit(search)
    }
}
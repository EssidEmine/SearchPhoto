package repository


import data.DataState
import kotlinx.coroutines.flow.Flow
import main.PhotosItem

interface SearchPhotosRepository {
    fun getPhotosSearch(): Flow<DataState<List<PhotosItem>>>
}
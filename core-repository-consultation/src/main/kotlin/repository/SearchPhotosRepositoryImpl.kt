package repository

import data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import main.PhotosItem
import repository.converter.toModel
import service.RetrofitConsultationService


class SearchPhotosRepositoryImpl constructor(
    private val consultationService: RetrofitConsultationService
) : SearchPhotosRepository {
    override fun getPhotosSearch(): Flow<DataState<List<PhotosItem>>> = flow {
        emit(DataState.Loading)
        try {
            val networkPhotosSearch = consultationService.getPhotos()
            val photosSearchModel = networkPhotosSearch.map {
                it.toModel()
            }
            emit(DataState.Success(photosSearchModel))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
    }




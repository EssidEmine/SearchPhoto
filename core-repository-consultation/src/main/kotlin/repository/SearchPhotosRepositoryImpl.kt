package repository

import android.util.Log
import com.example.core_network.consts.NoConnectivityException
import data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import main.PhotosItem
import repository.converter.toModel
import service.RetrofitConsultationService
import java.nio.channels.NoConnectionPendingException


class SearchPhotosRepositoryImpl constructor(
    private val consultationService: RetrofitConsultationService,
) : SearchPhotosRepository {

    override fun getPhotosSearch(search: String): Flow<DataState<PhotosItem>> = flow {
        emit(DataState.Loading)
        try {
            val networkPhotosSearch = consultationService.getPhotos(search)
            val photosSearchModel = networkPhotosSearch.toModel()
            emit(DataState.Success(photosSearchModel))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}




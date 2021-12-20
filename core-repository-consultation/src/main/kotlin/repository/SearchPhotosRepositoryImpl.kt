package repository

import android.util.Log
import com.example.core_network.consts.NoConnectivityException
import data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import main.PhotosItem
import repository.converter.toLocalModel
import repository.converter.toModel
import room.PhotosDao
import service.RetrofitConsultationService
import java.nio.channels.NoConnectionPendingException


class SearchPhotosRepositoryImpl constructor(
    private val consultationService: RetrofitConsultationService,
    private val photosPDao: PhotosDao
) : SearchPhotosRepository {


    // no DB Edition

    /*
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
         */

    // Room Edition

    override fun getPhotosSearch(): Flow<DataState<List<PhotosItem>>> = flow {
        emit(DataState.Loading)

        try {

            val data = photosPDao.getAllPictures().map { it.toModel() }
            emit(DataState.Success(data))

            val networkPhotosSearch = consultationService.getPhotos().take(10) // no need more for this démo
            photosPDao.nukeTable()
            val photosSearchModel = networkPhotosSearch.map {
                photosPDao.insert(it.toLocalModel())
                it.toModel()
            }
            emit(DataState.Success(photosSearchModel))
        } catch (e: Exception) {
            if (e.message.equals("oh no you suck !")) {
                Log.e("Oups", " Providing old data .....")
            } else {
                emit(DataState.Error(e))
            }
        }
    }

}




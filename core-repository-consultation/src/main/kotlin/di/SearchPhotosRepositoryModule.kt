package di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.SearchPhotosRepository
import repository.SearchPhotosRepositoryImpl
import room.PhotosDao
import service.RetrofitConsultationService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchPhotosRepositoryModule {

    @Singleton
    @Provides
    fun providePictureRepository(
        consultationService: RetrofitConsultationService,
        photosPDao: PhotosDao
    ): SearchPhotosRepository {
        return SearchPhotosRepositoryImpl(consultationService, photosPDao)
    }
}
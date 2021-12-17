package di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.SearchPhotosRepository
import repository.SearchPhotosRepositoryImpl
import service.RetrofitConsultationService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchPhotosRepositoryModule {

    @Singleton
    @Provides
    fun providePictureRepository(
        consultationService: RetrofitConsultationService
    ): SearchPhotosRepository {
        return SearchPhotosRepositoryImpl(consultationService)
    }
}
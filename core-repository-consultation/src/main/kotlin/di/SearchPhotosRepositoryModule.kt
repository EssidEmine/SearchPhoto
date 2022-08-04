package di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import repository.SearchPhotosRepository
import repository.SearchPhotosRepositoryImpl
import service.RetrofitConsultationService

@Module
@InstallIn(ViewModelComponent::class)
object SearchPhotosRepositoryModule {
    @Provides
    fun providePictureRepository(
        consultationService: RetrofitConsultationService,
    ): SearchPhotosRepository {
        return SearchPhotosRepositoryImpl(consultationService)
    }
}
package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import service.RetrofitConsultationService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitConsultationModule {

    @Singleton
    @Provides
    fun provideConsultationService(retrofit: Retrofit.Builder): RetrofitConsultationService {
        return retrofit
            .build()
            .create(RetrofitConsultationService::class.java)
    }
}
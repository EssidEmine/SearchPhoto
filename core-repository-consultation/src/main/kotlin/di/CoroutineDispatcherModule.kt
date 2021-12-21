package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import utils.DefaultDispatcher
import utils.DispatcherProvider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutineDispatcherModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): DispatcherProvider {
        return DefaultDispatcher()
    }
}
package di

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import room.PhotosDao
import room.PhotosDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabasePhotoSPModule {

    @Singleton
    @Provides
    fun providePhotosDatabase(@ApplicationContext context: Context): PhotosDatabase {
        return Room.databaseBuilder(
            context, PhotosDatabase::class.java,
            PhotosDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePictureLBCDao(database: PhotosDatabase): PhotosDao {
        return database.pictureLBCDao()
    }
}
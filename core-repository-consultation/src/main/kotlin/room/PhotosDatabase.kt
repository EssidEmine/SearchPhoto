package room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalSearchPhotos::class],
    version = PhotosDatabase.DB_VERSION,
    exportSchema = false
)
abstract class PhotosDatabase : RoomDatabase() {

    abstract fun pictureLBCDao(): PhotosDao

    companion object {

        const val DB_VERSION = 1
        const val DB_NAME = "photos_database"
    } }
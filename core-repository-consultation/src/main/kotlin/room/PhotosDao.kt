package room
import android.database.sqlite.SQLiteDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos_table ORDER BY id ASC")
    suspend fun getAllPictures(): List<LocalSearchPhotos>

    @Insert(onConflict = SQLiteDatabase.CONFLICT_REPLACE)
    suspend fun insert(photo: LocalSearchPhotos)

    @Query("DELETE FROM photos_table")
    suspend fun nukeTable()

}
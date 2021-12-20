package room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos_table")
class LocalSearchPhotos(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
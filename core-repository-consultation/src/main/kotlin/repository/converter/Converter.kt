package repository.converter

import main.PhotosItem
import model.SearchPhotosResponse

fun SearchPhotosResponse.toModel() = PhotosItem(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)


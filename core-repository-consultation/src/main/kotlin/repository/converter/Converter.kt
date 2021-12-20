package repository.converter

import main.PhotosItem
import model.SearchPhotosResponse
import room.LocalSearchPhotos

fun SearchPhotosResponse.toModel() = PhotosItem(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)

fun SearchPhotosResponse.toLocalModel() = LocalSearchPhotos(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)


fun LocalSearchPhotos.toModel() = PhotosItem(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)


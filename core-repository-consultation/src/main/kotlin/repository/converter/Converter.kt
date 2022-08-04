package repository.converter

import main.PhotosItem
import model.SearchPhotosResponse

fun SearchPhotosResponse.toModel() = PhotosItem(
    page = page,
    per_page = per_page,
    photos = photos.map {
        it.toModel()
    },
    total_results = total_results,
    next_page = next_page ?: ""
)

fun SearchPhotosResponse.Photo.toModel() = PhotosItem.Photo(
    id = id,
    url = url,
    photographer = photographer,
    src = PhotosItem.Photo.Src(
        large = src.large,
        medium = src.medium,
        small = src.small
    )
)





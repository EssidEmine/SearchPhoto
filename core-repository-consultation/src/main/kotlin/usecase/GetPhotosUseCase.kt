package usecase

import repository.SearchPhotosRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: SearchPhotosRepository) {
    fun getPhotosSearch() = repository.getPhotosSearch()
}
package service

import model.SearchPhotosResponse
import retrofit2.http.GET

interface RetrofitConsultationService {
    @GET("photos")
    suspend fun getPhotos(): List<SearchPhotosResponse>

}
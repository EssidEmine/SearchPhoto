package service

import model.SearchPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitConsultationService {
    @GET("search")
    suspend fun getPhotos(@Query("query") search: String): SearchPhotosResponse

}
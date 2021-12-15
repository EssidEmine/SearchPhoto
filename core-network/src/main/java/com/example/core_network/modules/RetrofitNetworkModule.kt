package com.example.core_network.modules

import com.example.core_network.consts.Config
import com.example.core_network.consts.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitNetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient.Builder {

        return OkHttpClient.Builder()
            .readTimeout(Config.readTimeout.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(Config.readTimeout.toLong(), TimeUnit.MILLISECONDS)
            .connectTimeout(Config.connectTimeout.toLong(), TimeUnit.MILLISECONDS)
            .followRedirects(true)
        // add Cache
        //Add interceptors
        // add Authenticator
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .client(provideHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
    }
}
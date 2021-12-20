package com.example.core_network.modules

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core_network.consts.Config
import com.example.core_network.consts.NoConnectivityException
import com.example.core_network.consts.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitNetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext appContext: Context): OkHttpClient.Builder {

        return OkHttpClient.Builder()
            .readTimeout(Config.readTimeout.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(Config.readTimeout.toLong(), TimeUnit.MILLISECONDS)
            .connectTimeout(Config.connectTimeout.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(NoConnectionInterceptor(appContext))
            .followRedirects(true)
        // add Cache
        //Add interceptors
        // add Authenticator
    }

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .client(provideHttpClient(appContext).build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    class NoConnectionInterceptor(private val appContext: Context): Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val isConnectionOn  = if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.M) {
                postAndroidMInternetCheck(connectivityManager)
            } else {
                preAndroidMInternetCheck(connectivityManager)
            }

            return if (!isConnectionOn) {
                throw NoConnectivityException()
            } else {
                chain.proceed(chain.request())
            }
        }

        private fun preAndroidMInternetCheck(
            connectivityManager: ConnectivityManager): Boolean {
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (activeNetwork != null) {
                return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                        activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
            }
            return false
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun postAndroidMInternetCheck(
            connectivityManager: ConnectivityManager): Boolean {
            val network = connectivityManager.activeNetwork
            val connection =
                connectivityManager.getNetworkCapabilities(network)

            return connection != null && (
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        }

    }


}
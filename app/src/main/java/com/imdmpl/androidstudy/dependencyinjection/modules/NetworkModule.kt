package com.imdmpl.androidstudy.dependencyinjection.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.imdmpl.androidstudy.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(private val apiBaseUrl: String) {
    fun provideApiBaseUrl(): String {
        return apiBaseUrl
    }

    @Provides
    internal fun provideOkHttpClient(
        cache: Cache, httpInterceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val client = OkHttpClient.Builder()
        client.cache(cache)

        // Add logging only for debug mode
        // Build Config of CORE module.
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            client.addInterceptor(logging)
//            client.addInterceptor(MockInterceptor())
            client.addNetworkInterceptor(StethoInterceptor())
        }

        // Attach all interceptors. Interceptors then must be registered in DI container too
        httpInterceptors.forEach { client.addInterceptor(it) }
        client.dispatcher(dispatcher)

        return client.build()
    }

    @Provides
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(apiBaseUrl)
            .client(okHttpClient)
            .build()
    }
}
package com.sendiko.simplynoteit.domain.di

import android.content.Context
import com.sendiko.simplynoteit.data.ApiService
import com.sendiko.simplynoteit.domain.preference.AppPreferences
import com.sendiko.simplynoteit.domain.preference.dataStore
import com.sendiko.simplynoteit.domain.repositories.TaskRepository
import com.sendiko.simplynoteit.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
            }.build())
        }.also {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            it.addInterceptor(logging)
        }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        val baseUrl = "https://justdoit-api.sendiko-softwarestudio.web.id/api/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService, preferences: AppPreferences): UserRepository {
        return UserRepository(apiService, preferences)
    }

    @Singleton
    @Provides
    fun provideTaskRepository(apiService: ApiService): TaskRepository {
        return TaskRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideDatastorePreferences(@ApplicationContext context: Context): AppPreferences{
        return AppPreferences(requireNotNull(context.dataStore))
    }
}
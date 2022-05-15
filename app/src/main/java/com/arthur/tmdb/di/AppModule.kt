package com.arthur.tmdb.di

import android.content.Context
import androidx.annotation.NonNull
import com.arthur.tmdb.BuildConfig
import com.arthur.tmdb.MyApplication
import com.arthur.tmdb.data.local.AppDatabase
import com.arthur.tmdb.utils.MyRequestInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context): MyApplication = app as MyApplication

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.invoke(context)
    }

    @Provides
    @Singleton
    fun provideMyHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MyRequestInterceptor())
            .addInterceptor(OkHttpProfilerInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, @NonNull okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.base_api_url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
}
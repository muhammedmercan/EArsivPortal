package com.example.e_arsivportal.di

import com.example.e_arsivportal.repo.Repository
import com.example.e_arsivportal.repo.RepositoryInterface
import com.example.e_arsivportal.service.Api
import com.example.e_arsivportal.utilities.DataHolder.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofitApi(): Api {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Api::class.java)


    }

    @Singleton
    @Provides
    fun injectNormalRepo(api: Api) = Repository(api) as RepositoryInterface
}
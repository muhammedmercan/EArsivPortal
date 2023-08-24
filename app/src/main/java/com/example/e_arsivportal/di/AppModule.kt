package com.example.e_arsivportal.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.e_arsivportal.repo.Repository
import com.example.e_arsivportal.repo.RepositoryInterface
import com.example.e_arsivportal.service.Api
import com.example.e_arsivportal.roomdb.Dao
import com.example.e_arsivportal.roomdb.Database
import com.example.e_arsivportal.utilities.DataHolder.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, Database::class.java,"ArtBookDB").build()

    @Singleton
    @Provides
    fun injectDao(
        database: Database
    ) = database.dao()


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
    fun injectNormalRepo(dao : Dao, api: Api, preferences: SharedPreferences) = Repository(dao,api,preferences) as RepositoryInterface


    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("mainPreference", Context.MODE_PRIVATE)
    }
}
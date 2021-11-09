package com.kurban.recep.di

import com.kurban.recep.common.Constants
import com.kurban.recep.data.RepositoryImp
import com.kurban.recep.data.api.Api
import com.kurban.recep.data.api.RemoteDataSource
import com.kurban.recep.domain.repository.Repository
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
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api): RemoteDataSource {
        return RemoteDataSource(api)
    }

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository {
        return RepositoryImp(remoteDataSource)
    }
}
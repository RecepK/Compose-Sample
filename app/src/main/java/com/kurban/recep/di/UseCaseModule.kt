package com.kurban.recep.di

import com.kurban.recep.domain.repository.Repository
import com.kurban.recep.domain.usecase.DataFetchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideDataFetchUseCase(repository: Repository): DataFetchUseCase {
        return DataFetchUseCase(repository)
    }
}
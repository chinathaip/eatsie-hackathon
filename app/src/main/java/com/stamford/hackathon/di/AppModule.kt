package com.stamford.hackathon.di


import com.stamford.hackathon.data.FoodDataSource
import com.stamford.hackathon.data.GetFoodRepositoryImpl
import com.stamford.hackathon.domain.GetFoodRepository
import com.stamford.hackathon.domain.GetFoodUseCase
import com.stamford.hackathon.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    viewModel { MainViewModel(get()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/v2/")//to be changed
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodDataSource::class.java)
    }

    factory { GetFoodUseCase(get()) }

    single<GetFoodRepository> { GetFoodRepositoryImpl(get()) }

}
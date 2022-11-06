package com.stamford.hackathon.di


import com.stamford.hackathon.data.FoodDataSource
import com.stamford.hackathon.data.GetFoodRepositoryImpl
import com.stamford.hackathon.domain.ClientPickupConfirmUseCase
import com.stamford.hackathon.domain.GetFoodRepository
import com.stamford.hackathon.domain.GetListingUseCase
import com.stamford.hackathon.domain.GetSortedListingUseCase
import com.stamford.hackathon.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    viewModel { MainViewModel(get(), get(), get()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://eatsie.vercel.app/api/")//to be changed
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodDataSource::class.java)
    }

    single<GetFoodRepository> { GetFoodRepositoryImpl(get()) }

    factory { GetListingUseCase(get()) }

    factory { GetSortedListingUseCase(get()) }

    factory { ClientPickupConfirmUseCase(get()) }
}
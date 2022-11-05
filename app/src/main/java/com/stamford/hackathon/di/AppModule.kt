package com.stamford.hackathon.di


import com.stamford.hackathon.data.FoodDataSource
import com.stamford.hackathon.data.GetFoodRepositoryImpl
import com.stamford.hackathon.domain.*
import com.stamford.hackathon.ui.main.MainViewModel
import com.stamford.hackathon.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    viewModel { MainViewModel(get(), get(), get()) }

    viewModel { ProfileViewModel(get()) }

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

    factory { GetListingByStatusUseCase(get()) }

    factory { ClientPickupConfirmUseCase(get()) }
}
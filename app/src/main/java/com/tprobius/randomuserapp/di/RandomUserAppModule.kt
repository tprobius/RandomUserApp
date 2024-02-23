package com.tprobius.randomuserapp.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.tprobius.randomuserapp.data.api.RandomUserApi
import com.tprobius.randomuserapp.data.api.RandomUserApi.Companion.BASE_URL
import com.tprobius.randomuserapp.data.repository.RandomUserApiRepositoryImpl
import com.tprobius.randomuserapp.domain.repository.RandomUserApiRepository
import com.tprobius.randomuserapp.domain.usecases.GetRandomUsersListUseCase
import com.tprobius.randomuserapp.presentation.userslist.UsersListViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideRandomUserApiRetrofit() }
    single { provideRandomUserApi(retrofitBuilder = get()) }

    factory<RandomUserApiRepository> {
        RandomUserApiRepositoryImpl(
            randomUserApi = get(),
            dispatcher = Dispatchers.IO
        )
    }
}

val databaseModule = module {

}

val useCasesModule = module {
    single { GetRandomUsersListUseCase(apiRepository = get()) }
}

val viewModelModule = module {
    viewModel { UsersListViewModel(getRandomUsersListUseCase = get()) }
}

val navigationModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
}

private fun provideRandomUserApiRetrofit(): Retrofit.Builder {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
    client.addInterceptor(logging)

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
}

private fun provideRandomUserApi(retrofitBuilder: Retrofit.Builder): RandomUserApi {
    return retrofitBuilder
        .build()
        .create(RandomUserApi::class.java)
}
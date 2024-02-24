package com.tprobius.randomuserapp.di

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.tprobius.randomuserapp.data.api.RandomUserApi
import com.tprobius.randomuserapp.data.api.RandomUserApi.Companion.BASE_URL
import com.tprobius.randomuserapp.data.database.RandomUserDatabase
import com.tprobius.randomuserapp.data.repository.RandomUserApiRepositoryImpl
import com.tprobius.randomuserapp.data.repository.RandomUserDatabaseRepositoryImpl
import com.tprobius.randomuserapp.domain.repository.RandomUserApiRepository
import com.tprobius.randomuserapp.domain.repository.RandomUserDatabaseRepository
import com.tprobius.randomuserapp.domain.usecases.GetRandomUsersListUseCase
import com.tprobius.randomuserapp.navigation.UserDetailsRouterImpl
import com.tprobius.randomuserapp.navigation.UsersListRouterImpl
import com.tprobius.randomuserapp.presentation.userdetails.UserDetailsRouter
import com.tprobius.randomuserapp.presentation.userdetails.UserDetailsViewModel
import com.tprobius.randomuserapp.presentation.userslist.UsersListRouter
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
    single { provideRecipeBookDatabase(app = get()).randomUserDao }
    single<RandomUserDatabaseRepository> {
        RandomUserDatabaseRepositoryImpl(
            randomUserDao = get(),
            dispatcher = Dispatchers.IO
        )
    }
}

val useCasesModule = module {
    single {
        GetRandomUsersListUseCase(
            apiRepository = get(),
            databaseRepository = get()
        )
    }
}

val viewModelModule = module {
    viewModel { UsersListViewModel(getRandomUsersListUseCase = get(), router = get()) }
    viewModel { UserDetailsViewModel(router = get()) }
}

val navigationModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }

    factory<UsersListRouter> { UsersListRouterImpl(get()) }
    factory<UserDetailsRouter> { UserDetailsRouterImpl(get()) }
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

fun provideRecipeBookDatabase(app: Application): RandomUserDatabase {
    return Room.databaseBuilder(
        app,
        RandomUserDatabase::class.java,
        RandomUserDatabase.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}
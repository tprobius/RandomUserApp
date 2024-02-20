package com.tprobius.randomuserapp.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val apiModule = module {

}

val databaseModule = module {

}

val useCasesModule = module {

}

val viewModelModule = module {

}

val navigationModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
}
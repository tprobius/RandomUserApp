package com.tprobius.randomuserapp

import android.app.Application
import com.tprobius.randomuserapp.di.apiModule
import com.tprobius.randomuserapp.di.databaseModule
import com.tprobius.randomuserapp.di.navigationModule
import com.tprobius.randomuserapp.di.useCasesModule
import com.tprobius.randomuserapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class RandomUserApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RandomUserApp)
            modules(
                apiModule,
                databaseModule,
                useCasesModule,
                viewModelModule,
                navigationModule
            )
        }
    }
}
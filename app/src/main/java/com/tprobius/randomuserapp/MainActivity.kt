package com.tprobius.randomuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.tprobius.randomuserapp.presentation.userslist.getUsersListScreen
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator = AppNavigator(this, R.id.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.newRootScreen(getUsersListScreen())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}
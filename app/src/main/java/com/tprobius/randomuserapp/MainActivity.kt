package com.tprobius.randomuserapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.tprobius.randomuserapp.presentation.userslist.UsersListFragment
import com.tprobius.randomuserapp.presentation.userslist.getUsersListScreen
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator = AppNavigator(this, R.id.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isFirst.add(
            getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getBoolean(
                IS_FIRST_ENTRY,
                true
            )
        )

        getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit()
            .putInt(UsersListFragment.LAST_POSITION, 0)
            .apply()

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

    override fun onDestroy() {
        super.onDestroy()
        getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit()
            .putInt(UsersListFragment.LAST_POSITION, 0)
            .apply()
    }

    companion object {
        const val APP_PREFERENCES = "app_preferences"
        const val IS_FIRST_ENTRY = "isFirstEntry"

        val isFirst = mutableListOf(true)
    }
}
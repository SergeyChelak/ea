package org.chelak.ea.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.chelak.ea.R
import org.chelak.ea.di.AppComponent
import org.chelak.ea.di.DaggerAppComponent
import org.chelak.ea.di.HostModule
import org.chelak.ea.di.RepositoryModule

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    val navController get() = Navigation.findNavController(this, R.id.nav_host_fragment)

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .hostModule(HostModule(this))
            .repositoryModule(RepositoryModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        val drawerFragments = setOf(R.id.myEstatesFragment, R.id.tariffsFragment, R.id.paymentHistoryFragment)
        appBarConfiguration = AppBarConfiguration.Builder(drawerFragments)
            .setOpenableLayout(drawerLayout)
            .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isDrawerFragment = drawerFragments.contains(destination.id)
            if (isDrawerFragment) {
                supportActionBar?.title = getString(R.string.app_name)
            }
//            supportActionBar?.setDisplayHomeAsUpEnabled(isDrawerFragment)
//            supportActionBar?.setHomeButtonEnabled(isDrawerFragment)
        }
        // Handle nav drawer item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
        setupWithNavController(navigationView, navController)
    }

}
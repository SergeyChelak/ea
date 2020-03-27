package org.chelak.ea.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.di.*

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
        toolbar.title = getString(R.string.app_name)
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
        appBarConfiguration = AppBarConfiguration.Builder(setOf(R.id.myEstatesFragment, R.id.tariffsFragment, R.id.paymentHistoryFragment))
            .setDrawerLayout(drawerLayout)
            .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar.let {
                val isVisible = destination.parent?.id == R.id.root_nav_graph
                it?.setDisplayHomeAsUpEnabled(isVisible)
                it?.setHomeButtonEnabled(isVisible)
            }
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

val Fragment.appComponent: AppComponent? get() = (activity as? MainActivity)?.component
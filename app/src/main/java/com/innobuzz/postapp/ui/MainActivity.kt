package com.innobuzz.postapp.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.innobuzz.postapp.MyAccessibilityService.Companion.isAccessibilitySettingsOn
import com.innobuzz.postapp.R
import com.innobuzz.postapp.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity for cupcake order flow.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        observe()
        enable_access.setOnClickListener {
            if (!isAccessibilitySettingsOn(this@MainActivity)) {
                val settingsIntent = Intent(
                    Settings.ACTION_ACCESSIBILITY_SETTINGS)
                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(settingsIntent)
            }
        }
    }

    private fun observe() {
        viewModel.showToast.observe(this) { success ->
            Toast.makeText(this@MainActivity,
                " ${success.getContentIfNotHandled()}",
                Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Handle navigation when the user chooses Up from the action bar.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
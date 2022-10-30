package com.elizav.sportquiz.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.elizav.sportquiz.R
import com.elizav.sportquiz.databinding.ActivityMainBinding
import com.elizav.sportquiz.domain.model.AppException.Companion.CONFIG_EX_MSG
import com.elizav.sportquiz.ui.utils.PhoneInfo.getDeviceName
import com.elizav.sportquiz.ui.utils.PhoneInfo.isSIMInserted
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        initObservers()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        navController = findNavController(R.id.nav_host_fragment_content_main)
    }

    fun changeTitle(newTitle: String) {
        binding.toolbar.title = newTitle
    }

    private fun initObservers() = with(mainViewModel) {
        path.observe(this@MainActivity) {
            it.fold(
                onSuccess = { url ->
                    if (url.isBlank() || getDeviceName().contains(
                            "google",
                            ignoreCase = true
                        ) || !isSIMInserted(this@MainActivity)
                    ) {
                        return@observe
                    }
                    navController.popBackStack(R.id.homeFragment, true);
                    navController.navigate(R.id.webFragment, args = bundleOf("url" to url))
                },
                onFailure = { ex ->
                    showSnackbar(ex.message ?: CONFIG_EX_MSG)
                }
            )
        }
    }

    private fun showSnackbar(text: String) = Snackbar.make(
        binding.root,
        text,
        Snackbar.LENGTH_LONG
    ).show()
}
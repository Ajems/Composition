package com.example.composition.presentation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.composition.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")

    private var statusBarHeight: Int? = null
    private var navigationBarHeight: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullScreen()
    }

    private fun setFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        increaseLayoutMargin()
        setStatusBarUiVisibility()
    }

    private fun setStatusBarUiVisibility() {
        if (isLightTheme(this)) {
            val systemDecorColor =
                (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
            window.decorView.systemUiVisibility = systemDecorColor
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    private fun isLightTheme(context: Context): Boolean {
        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_NO
    }

    private fun increaseLayoutMargin() {
        calculateStatusAndNavigationBar()
        if(statusBarHeight != null && navigationBarHeight != null) {
            binding.mainContainer.setPadding(
                0,
                statusBarHeight!!,
                0,
                navigationBarHeight!!
            )
        }
    }

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    private fun calculateStatusAndNavigationBar() {
        val resourceIdSB = resources.getIdentifier("status_bar_height", "dimen", "android")
        val resourceIdNB = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceIdSB > 0 && resourceIdNB > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceIdSB)
            navigationBarHeight = resources.getDimensionPixelSize(resourceIdNB)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
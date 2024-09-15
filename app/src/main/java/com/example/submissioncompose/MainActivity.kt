package com.example.submissioncompose

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color as ColorGraphics
import androidx.compose.ui.graphics.toArgb
import com.example.submissioncompose.ui.theme.colorGreenDark

import androidx.compose.ui.platform.LocalView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    private val tDelay = 3000L
    private var splashScreenShown = false

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set status bar transparent and adjust system UI
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        if (!splashScreenShown) {
            showSplashScreen()
        } else {
            setContent {
                SubmissionComposeTheme {
                    // Setting up system UI for transparent status bar
                    SideEffect {
                        window?.statusBarColor = Color.TRANSPARENT
                        WindowCompat.setDecorFitsSystemWindows(window, false)
                        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
                    }

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        HeyCow()
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun showSplashScreen() {
        setContentView(R.layout.activity_main)
        val splashView: View = findViewById(R.id.main)
        Handler(Looper.getMainLooper()).postDelayed({
            splashView.animate()
                .setDuration(500)
                .withEndAction {
                    splashScreenShown = true
                    setContent {
                        SubmissionComposeTheme {
                            SideEffect {
                                window?.statusBarColor = Color.TRANSPARENT
                                WindowCompat.setDecorFitsSystemWindows(window, false)
                                val insetsController = WindowInsetsControllerCompat(window, window.decorView)
                                insetsController.isAppearanceLightStatusBars = false // Nonaktifkan mode light untuk status bar

                                // Atur warna status bar sesuai dengan warna konten Anda
                                window?.statusBarColor = ColorGraphics(0xFF20A577).toArgb()

                                // Hindari garis putih dengan menetapkan warna yang solid
                                window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                            }

                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = ColorGraphics(0xFF20A577)
                            ) {
                                HeyCow()
                            }
                        }
                    }
                }
                .start()
        }, tDelay)
    }
}

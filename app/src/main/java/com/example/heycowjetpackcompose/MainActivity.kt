package com.example.heycowjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.heycowjetpackcompose.ui.theme.HeyCowJetpackComposeTheme
import android.view.animation.AnticipateInterpolator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import androidx.annotation.RequiresApi
import androidx.core.graphics.Insets
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.heycowjetpackcompose.ui.LoginScreen

import java.util.Objects;

class MainActivity : ComponentActivity() {
    private val tDelay = 3000L
    private var splashScreenShown = false

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val nama = data?.getStringExtra("NAMA") ?: ""
            val nim = data?.getStringExtra("NIM") ?: ""
            val gender = data?.getStringExtra("GENDER") ?: ""
            val saudara = data?.getStringExtra("SAUDARA") ?: ""
            val uangSaku = data?.getStringExtra("UANG_SAKU") ?: ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!splashScreenShown) {
            showSplashScreen()
        } else {
            setContent {
                LoginScreen(context = this, resultLauncher = resultLauncher)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun showSplashScreen() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val splashView: View = findViewById(R.id.main)

        ViewCompat.setOnApplyWindowInsetsListener(splashView) { v, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom)
            insets
        }
        Handler().postDelayed({
            splashView.animate()
                .translationY(-splashView.height.toFloat())
                .setDuration(500)
                .withEndAction {
                    splashScreenShown = true
                    setContent {
                        LoginScreen(context = this, resultLauncher = resultLauncher)
                    }
                }
                .start()
        }, tDelay)
    }
}
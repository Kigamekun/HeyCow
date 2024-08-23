package com.example.heycowjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button


import android.app.Activity
import android.content.Intent

class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Ambil data dari Intent
            val nama = intent.getStringExtra("NAMA") ?: "Default Value"
            val nim = intent.getStringExtra("NIM") ?: "Default Value"
            val gender = intent.getStringExtra("GENDER") ?: "Default Value"

            val saudara = intent.getStringExtra("SAUDARA") ?: "Default Value"
            val uangSaku = intent.getStringExtra("UANG_SAKU") ?: "0"

            // Tampilkan data dan tombol back
            ResultScreen(
                nama = nama,
                nim = nim,
                gender = gender,
                saudara = saudara,
                uangSaku = uangSaku,
                onBackPressed = {
                    // Kirim data kembali ke MainActivity
                    val resultIntent = Intent().apply {
                        putExtra("NAMA", nama)
                        putExtra("NIM", nim)
                        putExtra("GENDER", gender)
                        putExtra("SAUDARA", saudara)
                        putExtra("UANG_SAKU", uangSaku)
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish() // Menutup ResultActivity
                }
            )
        }
    }

    override fun onBackPressed() {
        // Kirim data kembali saat tombol back ditekan
        val resultIntent = Intent().apply {
            putExtra("NAMA", intent.getStringExtra("NAMA"))
            putExtra("NIM", intent.getStringExtra("NIM"))
            putExtra("GENDER", intent.getStringExtra("GENDER"))
            putExtra("SAUDARA", intent.getStringExtra("SAUDARA"))
            putExtra("UANG_SAKU", intent.getStringExtra("UANG_SAKU"))
        }
        setResult(Activity.RESULT_OK, resultIntent)
        super.onBackPressed()
    }
}

@Composable
fun ResultScreen(
    nama: String,
    nim: String,
    gender: String,
    saudara: String,
    uangSaku: String,
    onBackPressed: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text("Nama: $nama")
        Text("Nim: $nim")
        Text("Gender: $gender")
        Text("Saudara: $saudara")
        Text("Uang Saku: $uangSaku")

        // Tambahkan tombol "Back"
        Button(
            onClick = { onBackPressed() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Back")
        }
    }
}

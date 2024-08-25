package com.example.heycowjetpackcompose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.heycowjetpackcompose.ui.CustomStyleTextField
import com.example.heycowjetpackcompose.ui.CustomTextField
import com.example.heycowjetpackcompose.ui.GenderCard
import com.example.heycowjetpackcompose.ui.HeaderView
import com.example.heycowjetpackcompose.ui.theme.HeyCowJetpackComposeTheme

class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nama = intent.getStringExtra("NAMA") ?: "Default Value"
            val nim = intent.getStringExtra("NIM") ?: "Default Value"
            val gender = intent.getStringExtra("GENDER") ?: "Default Value"
            val saudara = intent.getStringExtra("SAUDARA") ?: "Default Value"
            val uangSaku = intent.getStringExtra("UANG_SAKU") ?: "0"

            ResultScreen(
                nama = nama,
                nim = nim,
                gender = gender,
                saudara = saudara,
                uangSaku = uangSaku,
                onBackPressed = {
                    val resultIntent = Intent().apply {
                        putExtra("NAMA", nama)
                        putExtra("NIM", nim)
                        putExtra("GENDER", gender)
                        putExtra("SAUDARA", saudara)
                        putExtra("UANG_SAKU", uangSaku)
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            )
        }
    }

    override fun onBackPressed() {
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
    MaterialTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConstraintLayout {
                    val (image, loginForm) = createRefs()
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(280.dp)
                            .constrainAs(image) {
                                top.linkTo(loginForm.top)
                                bottom.linkTo(loginForm.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        HeaderView()
                    }
                    Card(
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp)
                            .constrainAs(loginForm) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)
                        ) {

                            Text(
                                text = "Konfirmasi Data",
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            ResultField("Name", nama)
                            ResultField("NIM", nim)
                            ResultField(label = "Gender", value = gender)
                            ResultField(label = "Jumlah Saudara", value = saudara)
                            ResultField(label = "Uang Saku", value = uangSaku)

                            Button(
                                onClick = onBackPressed,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF1BA57B))
                            ) {
                                Text(
                                    text = "Back"
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultField(label: String, value: String) {
    Text(
        text = "$label:",
        style = MaterialTheme.typography.labelLarge.copy(color = Color(0xFF757575))
    )
    Text(
        text = value,
        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
    )
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewResultScreen() {
    HeyCowJetpackComposeTheme {
        ResultScreen(
            nama = "John Doe",
            nim = "123456789",
            gender = "Male",
            saudara = "2",
            uangSaku = "1000",
            onBackPressed = {}
        )
    }
}
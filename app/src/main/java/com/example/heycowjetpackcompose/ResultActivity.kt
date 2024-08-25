package com.example.heycowjetpackcompose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.heycowjetpackcompose.ui.HeaderView
import com.example.heycowjetpackcompose.ui.theme.HeyCowJetpackComposeTheme

class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
fun ResultField(label: String, value: String, iconResId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp) // Adjust padding as needed
    ) {
        // Add the Image composable to display the icon
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "$label icon",
            modifier = Modifier
                .size(24.dp) // Set the size of the icon
                .padding(end = 8.dp) // Add some padding between the icon and the text
        )

        Column {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.labelLarge.copy(color = Color(0xFF757575))
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
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

                    // Reuse HeaderView composable for the logo and header
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(280.dp)
                            .constrainAs(image) {
                                top.linkTo(loginForm.top)
                                bottom.linkTo(loginForm.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        HeaderView()  // Include the logo and header image here
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

                            ResultField(label = "Name", value = nama, iconResId = R.drawable.person_fill)
                            ResultField(label = "NIM", value = nim, iconResId = R.drawable.person_vcard_fill)
                            ResultField(label = "Gender", value = gender, iconResId = R.drawable.gender_male)
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

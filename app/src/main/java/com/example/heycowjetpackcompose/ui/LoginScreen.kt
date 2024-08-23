package com.example.heycowjetpackcompose.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.heycowjetpackcompose.ui.ui.theme.HeyCowJetpackComposeTheme
import androidx.compose.foundation.clickable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.heycowjetpackcompose.R
import com.example.heycowjetpackcompose.ResultActivity
import java.text.DecimalFormat

@Composable
fun LoginScreen(context: Context, resultLauncher: ActivityResultLauncher<Intent>) {

    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var saudara by remember { mutableStateOf("") }
    var uangSaku by remember { mutableStateOf("") }

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

                            val loginText = "Gabung dan Awasi Kesehatan Ternak Anda!"
                            val loginAnnotatedString = buildAnnotatedString {
                                append(loginText)
                                addStyle(
                                    style = SpanStyle(
                                        color = Color(0xFFFF1BA57B),
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                                    ),
                                    start = 0,
                                    end = 6 // Panjang kata "Gabung"
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = Color(0xFFFF1BA57B),
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                                    ),
                                    start = 11, // Posisi awal kata "Awasi"
                                    end = 16 // Panjang kata "Awasi"
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = Color(0xFF757575),
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
                                    ),
                                    start = 6,
                                    end = 11 // Posisi dari " dan "
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = Color(0xFF757575),
                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
                                    ),
                                    start = 16,
                                    end = loginText.length // Sisanya
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 0.dp, bottom = 0.dp),
                                text = loginAnnotatedString,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                            )
                            Text(
                                text = "Nama",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF757575)),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )

                            CustomStyleTextField(
                                "Nama",
                                R.drawable.person_fill,
                                KeyboardType.Text,
                                VisualTransformation.None,
                                value = nama,
                                onValueChange = { nama = it }
                            )

                            Text(
                                text = "NIM",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF757575)),
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            CustomStyleTextField(
                                "NIM",
                                R.drawable.person_vcard_fill,
                                KeyboardType.Text,
                                VisualTransformation.None,
                                value = nim,
                                onValueChange = { nim = it }
                            )
                            Text(
                                text = "Gender",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF757575)),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                GenderCard(
                                    label = "Male",
                                    iconResId = R.drawable.gender_male,
                                    selected = selectedGender == "Male",
                                    onClick = { selectedGender = "Male" },
                                    modifier = Modifier
                                        .weight(1f)

                                )
                                GenderCard(
                                    label = "Female",
                                    iconResId = R.drawable.gender_female,
                                    selected = selectedGender == "Female",
                                    onClick = { selectedGender = "Female" },
                                    modifier = Modifier
                                        .weight(1f)

                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                CustomTextField(
                                    placeHolder = "Jml Saudara",
                                    keyboardType = KeyboardType.Number,
                                    visualTransformation = VisualTransformation.None,
                                    modifier = Modifier.weight(1f),
                                    value = saudara,
                                    onValueChange = { saudara = it }
                                )
                                CustomTextField(
                                    placeHolder = "Uang Saku",
                                    keyboardType = KeyboardType.Number,
                                    visualTransformation = VisualTransformation.None,
                                    modifier = Modifier.weight(1f),
                                    value = uangSaku,
                                    onValueChange = { input ->
                                        val cleanedInput = input.replace(",", "")
                                        val formattedInput = if (cleanedInput.isNotEmpty()) {
                                            String.format("%,d", cleanedInput.toLong())
                                        } else {
                                            ""
                                        }
                                        uangSaku = formattedInput
                                    }
                                )
                            }

                            Button(
                                onClick = {
                                    val intent = Intent(context, ResultActivity::class.java).apply {
                                        putExtra("NAMA", nama)
                                        putExtra("NIM", nim)
                                        putExtra("GENDER", selectedGender)
                                        putExtra("SAUDARA", saudara)
                                        putExtra("UANG_SAKU", uangSaku)
                                    }
                                    resultLauncher.launch(intent)
                                },
                                modifier = Modifier
                                    .padding(top = 30.dp, bottom = 34.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFF1BA57B)
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                    text = "Submit",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomStyleTextField(
    placeHolder: String,
    leadingIconId: Int,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    value : String,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Image(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .size(18.dp),
                        painter = painterResource(id = leadingIconId),  // material icon
                        colorFilter = ColorFilter.tint(Color(0xFFFF1BA57B)),
                        contentDescription = "custom_text_field"
                    )
                    Canvas(
                        modifier = Modifier.height(24.dp)
                    ) {
                        // Allows you to draw a line between two points (p1 & p2) on the canvas.
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2.0F
                        )
                    }
                }
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF1BA57B),
            unfocusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        visualTransformation = visualTransformation
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    placeHolder: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    modifier: Modifier = Modifier,
    value : String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = { Text(text = placeHolder) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF1BA57B),
            unfocusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        visualTransformation = visualTransformation
    )
}



@Composable
fun GenderCard(
    label: String,
    iconResId: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .padding(top = 8.dp, bottom = 8.dp)
            .border(
                width = 1.dp, // Ketebalan border
                color = if (selected) Color(0xFF25BB7F) else Color.White, // Warna border, dapat disesuaikan
                shape = RoundedCornerShape(8.dp) // Bentuk border, sesuaikan dengan bentuk Card
            ),

        colors = CardDefaults.cardColors(
            containerColor = if (selected) Color(0xFF25BB7F) else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Pastikan kolom mengambil seluruh ruang yang tersedia
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Elemen di dalam kolom tetap di tengah

        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

        }
    }
}

@Composable
fun HeaderView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF1BA57B))
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 40.dp)
    ) {
        Image(
            modifier = Modifier.width(100.dp),
            bitmap = ImageBitmap.imageResource(id = R.drawable.logo),
            contentDescription = "header_view_flower_logo"
        )
        Text(
            text = "HeyCow",
            color = Color.White,
            modifier = Modifier.padding(top = 15.dp),
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.opensaucesansblack)),
                letterSpacing = 2.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val context = LocalContext.current
    val dummyLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { }
    LoginScreen(context = context, resultLauncher = dummyLauncher)
}
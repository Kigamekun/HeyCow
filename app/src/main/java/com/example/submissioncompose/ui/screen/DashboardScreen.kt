package com.example.submissioncompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.submissioncompose.R
import com.example.submissioncompose.ui.theme.background
import com.example.submissioncompose.ui.theme.colorPrimary

@Composable
fun DashboardScreen() {
    val gradientHeight = 60.dp
    val density = LocalDensity.current
    val gradientHeightPx = with(density) { gradientHeight.toPx() }


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Stacked cards in a Box
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp) // Adjust height based on your needs
            ) {
                // Background gradient card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(271.dp)
                        .align(Alignment.TopCenter)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF20A577),
                                    Color(0xFF64CFAA),
                                ),
                                start = Offset.Zero,
                                end = Offset(0F, 271F),
                            ),
                            shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
                        )
                )

                // White card stacked on top of the gradient card
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(30.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .width(390.dp)
                        .height(162.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = 40.dp)  // Adjust offset to control the overlap
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {

                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    text = "More content here...",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp),
                    color = Color.Black
                )
                GridCard()

            }
        }
        item {
            Text(
                text = "More content here...",
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        }
        items(10) {
            CardItem()
        }


    }
}


@Composable
fun GridCard() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Left side - Single large box
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .background(
                        color = Color(0xFF64CFAA),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Left Box",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right side - Two smaller vertical boxes
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF20A577),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Top Right Box",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF20A577),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Bottom Right Box",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }


@Composable
fun CardItem(){
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),

        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom=16.dp, start=16.dp, end=16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.arberdeen),
                contentDescription = "favorite_cow_image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "sadas",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "323",
                    style = TextStyle(
                        color = colorPrimary,
                        fontSize = 16.sp,
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

        }
    }
}

@Preview
@Composable
fun PreviewDashboardScreen() {
    Surface {
        DashboardScreen()
    }
}

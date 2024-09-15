package com.example.submissioncompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.submissioncompose.data.CowsData
import com.example.submissioncompose.ui.theme.colorPrimary
import com.example.submissioncompose.ui.viewmodel.MainViewModel


@Composable
fun DetailScreen(
    cowId: Int,
    navController: NavController,
    viewModel: MainViewModel,
) {
    val favoriteCows by viewModel.favoriteCows.collectAsState()
    val cow = CowsData.list.find { it.id == cowId }
    val isFavorite = cow?.id  in favoriteCows

    cow?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),

                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = cow.image),
                    contentDescription = cow.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)

                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = cow.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = cow.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(

                        text = cow.price,
                        style = MaterialTheme.typography.headlineMedium,
                        color = colorPrimary,
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }



            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(text = "Back",color= colorPrimary)
                }

                Button(
                    onClick = { viewModel.toggleFavorite(cow) },
                    shape= RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorPrimary)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        tint = Color.White,
                        modifier = Modifier.padding(5.dp),
                        contentDescription = "cow_card_icon"
                    )
                    Text(text = "Add to Favorites",color= Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val cowId = 1
    val navController = rememberNavController()
    val viewModel = MainViewModel()

    DetailScreen(
        cowId = cowId,
        navController = navController,
        viewModel = viewModel
    )
}
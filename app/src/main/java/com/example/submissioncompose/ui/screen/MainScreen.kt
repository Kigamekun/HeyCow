package com.example.submissioncompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.submissioncompose.HeyCow
import com.example.submissioncompose.data.Cows
import com.example.submissioncompose.data.CowsData
import com.example.submissioncompose.R
import com.example.submissioncompose.ui.theme.colorPrimary
import com.example.submissioncompose.ui.theme.colorSecondary
import com.example.submissioncompose.ui.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val favoriteCows by viewModel.favoriteCows.collectAsState()
    // Filter Cows based on search query
    val filteredCows = remember(searchQuery) {
        CowsData.list.filter { cow ->
            cow.name.contains(searchQuery, ignoreCase = true)
        }
    }

    LazyColumn(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SlidingBanner()
        }
        item {
            SearchBar(
                query = searchQuery, onQueryChange = { searchQuery = it })
        }
        item {
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "List Cows",
                    style = TextStyle(
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
        item {
            PopularCowsList(
                cows = filteredCows,
                onCowClick = { cow ->
                    navController.navigate("detail/${cow.id}")
                },
                onFavoriteClick = { cow ->
                    viewModel.toggleFavorite(cow)
                },
                favoriteCows = favoriteCows
            )
        }
    }
}


@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = { newValue -> onQueryChange(newValue) },
        label = { Text("Search Cows") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp,bottom=20.dp),
        singleLine = true
    )
}


@Composable
private fun PopularCowsList(
    cows: List<Cows>,
    onCowClick: (Cows) -> Unit,
    onFavoriteClick: (Cows) -> Unit,
    favoriteCows: Set<Int>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(cows) { cow ->
            CowCard(
                cow = cow,
                isFavorite = cow.id in favoriteCows,
                onClick = { onCowClick(cow) },
                onFavoriteClick = { onFavoriteClick(cow) }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SlidingBanner() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = 3,
        state = pagerState,
        itemSpacing = 20.dp,
    ) { _ ->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(16.dp)),
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_sale_banner),
            contentScale = ContentScale.FillWidth,
            contentDescription = "sliding_banner_image"
        )
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .padding(16.dp),
    )
}

@Composable
fun CowCard(
    cow: Cows,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(1.dp)
                    .clip(RoundedCornerShape(16.dp)),
                bitmap = ImageBitmap.imageResource(id = cow.image),
                contentDescription = "cow_card",
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = cow.name,
                        style = TextStyle(
                            color = colorSecondary,
                            fontSize = 16.sp,
                        )
                    )
                    Text(
                        text = cow.price,
                        style = TextStyle(
                            color = colorPrimary,
                            fontSize = 16.sp,
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = if (isFavorite) Color.Red else colorPrimary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onFavoriteClick() }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        tint = Color.White,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = "cow_card_icon"
                    )
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    HeyCow()
}



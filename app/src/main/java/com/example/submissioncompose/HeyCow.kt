package com.example.submissioncompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.submissioncompose.navigation.NavigationItem
import com.example.submissioncompose.navigation.Screen
import com.example.submissioncompose.ui.screen.MainScreen
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.submissioncompose.ui.screen.DashboardScreen
import com.example.submissioncompose.ui.screen.DetailScreen
import com.example.submissioncompose.ui.screen.FavoritesScreen
import com.example.submissioncompose.ui.screen.ProfileScreen
import com.example.submissioncompose.ui.theme.background
import com.example.submissioncompose.ui.theme.colorPrimary
import com.example.submissioncompose.ui.theme.ghost_white
import com.example.submissioncompose.ui.viewmodel.MainViewModel

@Composable
fun HeyCow(
    modifier: Modifier = Modifier,
) {
    val viewModel = MainViewModel()
    val selectedIndex = remember { mutableStateOf(0) }
    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            content = { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        top = paddingValues.calculateTopPadding(),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                        bottom = paddingValues.calculateBottomPadding() + 16.dp // Add extra bottom padding here
                    )
                ) {
                    composable(Screen.Home.route) {
                        DashboardScreen()
                    }
                    composable(Screen.Favorite.route) {
                        FavoritesScreen(
                            viewModel = viewModel,
                            navController = navController,
                            )
                    }
                    composable(Screen.Profile.route) {
                        ProfileScreen( )
                    }
                }
            },
            bottomBar = {
                CustomBottomBar(selectedIndex = selectedIndex, navController = navController)
            }
        )
    }
}



@Composable
fun CustomBottomBar(
    selectedIndex: MutableState<Int>,
    navController: NavHostController,
) {
    val listItems = listOf("Home", "Favorite", "Profile")
    val navigationBarPadding = WindowInsets.safeDrawing
        .only(WindowInsetsSides.Bottom)
        .asPaddingValues()

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom=40.dp, start = 16.dp, end = 16.dp)
            .height(64.dp)
    ) {
        NavigationBar(containerColor = Color.White) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                NavigationBarItem(
                    modifier = Modifier.padding(top = 20.dp),

                    icon = {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            when (index) {
                                0 -> Icon(
                                    Icons.Default.Home,
                                    contentDescription = label,
                                )
                                1 -> Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = label
                                )
                                2 -> Icon(
                                    Icons.Default.Person,
                                    contentDescription = label
                                )
                            }
                        }
                    },
                    selected = isSelected,
                    onClick = {
                        selectedIndex.value = index
                        when (index) {
                            0 -> {
                                navController.popBackStack(Screen.Home.route, inclusive = false)
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                            1 -> {
                                navController.popBackStack(Screen.Favorite.route, inclusive = false)
                                navController.navigate(Screen.Favorite.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                            2 -> {
                                navController.popBackStack(Screen.Profile.route, inclusive = false)
                                navController.navigate(Screen.Profile.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorPrimary,
                        unselectedIconColor = Color.Gray
                    )
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    val viewModel = MainViewModel()
    HeyCow()
}
package com.example.submissioncompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.submissioncompose.data.CowsData
import com.example.submissioncompose.navigation.Screen
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
import com.example.submissioncompose.ui.viewmodel.MainViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class HeyCowKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController


    @get:Rule
    val noOpRule = NoOpTestRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {

            SubmissionComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())

                HeyCow(
                    viewModel = MainViewModel(),
                    modifier = Modifier
                )
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("lazy_list").performScrollToIndex(5)
        composeTestRule.onNodeWithText(CowsData.list[3].name).performClick()

        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(CowsData.list[3].name).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navigateTo_AboutPage() {
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.name_author).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.email_author).assertIsDisplayed()
    }

    @Test
    fun searchShowEmptyList() {
        val incorrectSearch = "aa31z"
        composeTestRule.onNodeWithStringId(R.string.search_text).performTextInput(incorrectSearch)
        composeTestRule.onNodeWithTag("emptyList").assertIsDisplayed()
    }

    @Test
    fun searchShowList() {
        val rightSearch = "rose"
        composeTestRule.onNodeWithStringId(R.string.search_text).performTextInput(rightSearch)
        composeTestRule.onNodeWithText("rose").assertIsDisplayed()
    }

    @Test
    fun favoriteClickInDetailScreen_ShowInFavoriteScreen() {
        composeTestRule.onNodeWithText(CowsData.list[0].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithTag("favorite_detail_button").performClick()
        composeTestRule.onNodeWithTag("back_home").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(CowsData.list[0].name).assertIsDisplayed()
    }

    @Test
    fun favoriteClickAndDeleteFavoriteInDetailScreen_NotShowInFavoriteScreen() {
        composeTestRule.onNodeWithText(CowsData.list[0].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithTag("favorite_detail_button").performClick()
        composeTestRule.onNodeWithTag("favorite_detail_button").performClick()
        composeTestRule.onNodeWithTag("back_home").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithStringId(R.string.empty_favorite).assertIsDisplayed()
    }

    @Test
    fun favoriteClickInHome_ShowInFavoriteScreen() {
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithTag("lazy_list").performScrollToIndex(0)
        composeTestRule.onNodeWithText(CowsData.list[0].name).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("item_favorite_button").onFirst().performClick()
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(CowsData.list[0].name).assertIsDisplayed()
    }

    @Test
    fun favoriteClickAndDeleteFavoriteInHome_NotShowInFavoriteScreen() {
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithTag("lazy_list").performScrollToIndex(0)
        composeTestRule.onNodeWithText(CowsData.list[0].name).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("item_favorite_button").onFirst().performClick()
        composeTestRule.onAllNodesWithTag("item_favorite_button").onFirst().performClick()
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithStringId(R.string.empty_favorite).assertIsDisplayed()
    }
}
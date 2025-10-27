package com.juagosin.readingAPP.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.juagosin.readingAPP.presentation.screens.addbook.AddBookScreen
import com.juagosin.readingAPP.presentation.screens.detail.DetailScreen
import com.juagosin.readingAPP.presentation.screens.edit.EditScreen
import com.juagosin.readingAPP.presentation.screens.home.HomeScreen
import com.juagosin.readingAPP.presentation.screens.list.ListScreen
import com.juagosin.readingAPP.presentation.screens.search.SearchScreen


@Composable
fun MyNavHost(navController: NavHostController, modifier: Modifier) {
    val DETAIL_ROUTE = "detail/{id}"
    val EDIT_ROUTE = "edit/{id}"

    NavHost(navController, startDestination = AppScreen.Home.route, modifier = modifier) {
        composable(AppScreen.Home.route) {
            HomeScreen(onItemClick = {

                    bookId ->
                navController.navigate("detail/$bookId")
            })
        }
        composable(AppScreen.List.route) {
            ListScreen(
                onItemClick = {

                        bookId ->
                    navController.navigate("detail/$bookId")
                }
            )
        }
        composable(
            route = DETAIL_ROUTE,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("id") ?: 0
            DetailScreen(bookId = bookId)
        }
        composable(
            route = "addbook?title={title}&author={author}&imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("author") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val author = backStackEntry.arguments?.getString("author")
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")
            AddBookScreen(
                onBookSaved = {
                    navController.navigate(AppScreen.List.route)
                },
                prefilledTitle = title,
                prefilledAuthor = author,
                prefilledImageUrl = imageUrl
            )
        }
        composable(
            route = EDIT_ROUTE,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("id") ?: 0
            EditScreen(
                onBookEdited = {
                    navController.navigate(AppScreen.List.route)
                },
                bookId = bookId
            )
        }
        composable(AppScreen.Search.route) {
            SearchScreen(onItemClick = { title, author, imageUrl ->
                navController.navigate("addbook?title=$title&author=$author&imageUrl=$imageUrl")

            })
        }


    }

}
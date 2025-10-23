package com.juagosin.estudio.presentation.navigation

import android.R.attr.label
import android.R.attr.onClick
import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar() {

        NavigationBarItem(
            label = {
                Text(text = stringResource(AppScreen.Home.titleRes))
            },
            selected = currentRoute == AppScreen.Home.route,
            onClick = {
                navController.navigate(AppScreen.Home.route) {
                    popUpTo(AppScreen.Home.route) {
                        inclusive = true
                    }
                }
            },
            icon = {
                Icon(imageVector = AppScreen.Home.icon, contentDescription = "")
            }
        )
        NavigationBarItem(
            label = {
                Text(text = stringResource(AppScreen.List.titleRes))
            },
            selected = currentRoute == AppScreen.List.route,
            onClick = {
                navController.navigate(AppScreen.List.route) {
                    popUpTo(AppScreen.List.route) {
                        inclusive = true
                    }
                }
            },
            icon = {
                Icon(imageVector = AppScreen.List.icon, contentDescription = "")
            }
        )




    }
}
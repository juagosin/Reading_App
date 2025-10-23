package com.juagosin.readingAPP.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.juagosin.readingAPP.R
import com.juagosin.readingAPP.presentation.screens.detail.DetailEvent
import com.juagosin.readingAPP.presentation.screens.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(modifier: Modifier) {
    val navController = rememberNavController()
    val viewModel: DetailViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val currentId = navBackStackEntry?.arguments?.getInt("id")
            if (currentRoute?.startsWith("detail") == true) {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.screen_title_detail)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.btn_back))
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */
                            navController.navigate("edit/$currentId")
                        }) {
                            Icon(Icons.Filled.Edit, contentDescription = stringResource(R.string.btn_edit))
                        }

                        IconButton(onClick = {
                            /* doSomething() */
                            viewModel.onEvent(DetailEvent.DeleteBook(currentId!!))
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.Delete, contentDescription = stringResource(R.string.btn_delete))
                        }

                    }
                )
            }
        },
        bottomBar = {
            BottomBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppScreen.AddBook.route)
                }
            ) {
                Icon(Icons.Filled.BookmarkAdd, stringResource(R.string.btn_addbook))

            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        MyNavHost(navController, modifier.padding(innerPadding))

    }

}

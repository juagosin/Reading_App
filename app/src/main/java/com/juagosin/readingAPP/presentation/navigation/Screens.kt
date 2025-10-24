package com.juagosin.readingAPP.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.juagosin.readingAPP.R
import kotlinx.serialization.Serializable



interface BottomBarScreen {
    val titleRes: Int
    val icon: ImageVector
    val showInBottomBar: Boolean
}

@Serializable
sealed class AppScreen {
    abstract val route: String

    @Serializable
    data object Home : AppScreen(), BottomBarScreen {
        // Propiedad serializable de AppScreen
        override val route: String = "home"

        // Propiedades de la UI de la interfaz BottomBarScreen
        override val titleRes: Int = R.string.screen_title_home
        override val icon: ImageVector = Icons.Default.Home
        override val showInBottomBar: Boolean = true
    }

    @Serializable
    data object List : AppScreen(), BottomBarScreen {
        override val route: String = "list"
        override val titleRes: Int =  R.string.screen_title_list
        override val icon: ImageVector = Icons.Filled.Bookmarks
        override val showInBottomBar: Boolean = true
    }

    @Serializable
    data class Detail(val id: Int) : AppScreen(), BottomBarScreen {
        override val route: String = "detail/$id"
        override val titleRes: Int = R.string.screen_title_detail
        override val icon: ImageVector
            get() = Icons.Default.Person
        override val showInBottomBar: Boolean = true
    }

    @Serializable
    data object AddBook : AppScreen() {
        override val route: String = "add"
        val title: String = R.string.screen_title_add.toString()

    }
    @Serializable
    data class Edit(val id: Int) : AppScreen(), BottomBarScreen {
        override val route: String = "edit/$id"
        override val titleRes: Int = R.string.screen_title_edit
        override val icon: ImageVector
            get() = Icons.Default.Person
        override val showInBottomBar: Boolean = true
    }

    @Serializable
    data object Search : AppScreen(), BottomBarScreen {
        // Propiedad serializable de AppScreen
        override val route: String = "search"

        // Propiedades de la UI de la interfaz BottomBarScreen
        override val titleRes: Int = R.string.screen_title_search
        override val icon: ImageVector = Icons.Default.Search
        override val showInBottomBar: Boolean = true
    }

}



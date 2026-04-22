package com.ElOuedUniv.maktaba.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object BookList : Screen("book_list")
    data object Categories : Screen("categories")
    data object AddBook : Screen("add_book")
    data object BookDetail : Screen("book_detail/{isbn}") {
        fun createRoute(isbn: String) = "book_detail/$isbn"

        val arguments = listOf(
            navArgument("isbn") { type = NavType.StringType }
        )
    }
}
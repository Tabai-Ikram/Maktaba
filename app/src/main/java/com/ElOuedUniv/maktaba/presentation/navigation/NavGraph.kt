package com.ElOuedUniv.maktaba.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ElOuedUniv.maktaba.presentation.book.BookListView
import com.ElOuedUniv.maktaba.presentation.book.add.AddBookView
import com.ElOuedUniv.maktaba.presentation.book.detail.BookDetailView  // ⭐ تغيير: BookDetailView بدلاً من BookDetailScreen
import com.ElOuedUniv.maktaba.presentation.category.CategoryListView

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.BookList.route
    ) {
        // شاشة قائمة الكتب
        composable(Screen.BookList.route) {
            BookListView(
                onCategoriesClick = {
                    navController.navigate(Screen.Categories.route)
                },
                onAddBookClick = {
                    navController.navigate(Screen.AddBook.route)
                },
                onBookClick = { isbn ->
                    navController.navigate(Screen.BookDetail.createRoute(isbn))
                }
            )
        }

        // شاشة تفاصيل الكتاب
        composable(
            route = Screen.BookDetail.route,
            arguments = Screen.BookDetail.arguments
        ) { backStackEntry ->
            val isbn = backStackEntry.arguments?.getString("isbn") ?: ""
            BookDetailView(  // ⭐ تغيير: BookDetailView بدلاً من BookDetailScreen
                isbn = isbn,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // شاشة إضافة كتاب
        composable(Screen.AddBook.route) {
            AddBookView(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // شاشة الفئات
        composable(Screen.Categories.route) {
            CategoryListView(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
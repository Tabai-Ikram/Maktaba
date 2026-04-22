package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    private val _categoriesList = listOf(
        Category(id = "1", name = "Programming",  description = "Books about software development and coding",    iconRes = android.R.drawable.ic_menu_edit),
        Category(id = "2", name = "Algorithms",   description = "Books about algorithms and data structures",    iconRes = android.R.drawable.ic_menu_sort_by_size),
        Category(id = "3", name = "Databases",    description = "Books about database design and management",   iconRes = android.R.drawable.ic_menu_agenda),
        Category(id = "4", name = "Mathematics",  description = "Pure and applied mathematics",                 iconRes = android.R.drawable.ic_menu_compass),
        Category(id = "5", name = "Networks",     description = "Computer networks and security",               iconRes = android.R.drawable.ic_menu_share),
    )

    private val categoriesFlow = MutableSharedFlow<List<Category>>(replay = 1).apply {
        tryEmit(_categoriesList)
    }
    
    override fun getAllCategories(): Flow<List<Category>> = flow {
        delay(2000) // Simulate delay
        emitAll(categoriesFlow)
    }

    override fun getCategoryById(id: String): Category? {
        return _categoriesList.find { it.id == id }
    }
}

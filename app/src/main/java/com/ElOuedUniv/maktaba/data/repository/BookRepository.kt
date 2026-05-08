package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBooks(): Flow<List<Book>>
    fun getBookByIsbn(isbn: String): Book?
    // أضفنا imageBytes لرفع الصورة اختيارياً
    suspend fun addBook(book: Book, imageBytes: ByteArray? = null)
}

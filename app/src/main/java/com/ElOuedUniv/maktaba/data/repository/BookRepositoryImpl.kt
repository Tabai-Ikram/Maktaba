package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf<Book>()

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1)
    
    override fun getAllBooks(): Flow<List<Book>> = flow {
        emitAll(booksFlow)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    // تصحيح: إضافة المعاملات الجديدة وتعديل النوع ليكون suspend
    override suspend fun addBook(book: Book, imageBytes: ByteArray?) {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList())
    }
}

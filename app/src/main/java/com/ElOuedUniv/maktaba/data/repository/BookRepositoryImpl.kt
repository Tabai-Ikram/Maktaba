package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

class BookRepositoryImpl : BookRepository {

    private val booksList = listOf(
        Book(isbn = "11111", title = "Clean Code", nbPages = 150),
        Book(isbn = "22222", title = "The Pragmatic Programmer", nbPages = 200),
        Book(isbn = "33333", title = "Design Patterns", nbPages = 210),
        Book(isbn = "444444", title = "Refactoring", nbPages = 120),
        Book(isbn = "55555", title = "Head First Design Patterns", nbPages = 300)
    )
    
    override fun getAllBooks(): List<Book> {
        return booksList
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}


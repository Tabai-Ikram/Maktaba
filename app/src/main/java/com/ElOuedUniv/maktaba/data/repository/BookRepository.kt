package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

/**
 * Repository for managing book data
 * This follows the Repository pattern to abstract data sources
 */
class BookRepository {

    /**
     * TODO for Students (TP1 - Exercise 1):
     * Complete the book information for each book in the list below.
     * Add the following information for each book:
     * - isbn: Use a valid ISBN-13 format (e.g., "978-3-16-148410-0")
     * - nbPages: Add the actual number of pages
     *
     * Example:
     * Book(
     *     isbn = "978-0-13-468599-1",
     *     title = "Clean Code",
     *     nbPages = 464
     * )
     */
    private val booksList = listOf(
        Book(isbn = "978-0-13-235088-4", title = "Clean Code", nbPages = 464),
        Book(isbn = "978-0-13-595705-9", title = "The Pragmatic Programmer", nbPages = 320),
        Book(isbn = "978-0-20-163361-0", title = "Design Patterns", nbPages = 395),
        Book(isbn = "978-0-20-148567-7", title = "Refactoring", nbPages = 431),
        Book(isbn = "978-0-59-600712-6", title = "Head First Design Patterns", nbPages = 688),

        Book(isbn = "978-0-13-449416-6", title = "Clean Architecture", nbPages = 432),
        Book(isbn = "978-0-32-112521-7", title = "Domain-Driven Design", nbPages = 560),
        Book(isbn = "978-0-13-117705-5", title = "Working Effectively with Legacy Code", nbPages = 456),
        Book(isbn = "978-0-73-561967-8", title = "Code Complete", nbPages = 960),
        Book(isbn = "978-0-20-183595-3", title = "The Mythical", nbPages = 336)
    )

    /**
     * TODO for Students (TP1 - Exercise 2):
     * Add 5 more books to the list above.
     * Choose books related to Computer Science, Programming, or any topic you like.
     * Remember to include complete information (ISBN, title, nbPages).
     *
     * Tip: You can find ISBN numbers for books on:
     * - Google Books
     * - Amazon
     * - GoodReads
     */

    /**
     * Get all books from the repository
     * @return List of all books
     */
    fun getAllBooks(): List<Book> {
        return booksList
    }

    /**
     * Get a book by ISBN
     * @param isbn The ISBN of the book to find
     * @return The book if found, null otherwise
     */
    fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}

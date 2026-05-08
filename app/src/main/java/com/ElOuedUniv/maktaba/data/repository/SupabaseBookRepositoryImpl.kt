package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SupabaseBookRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> = flow {
        // استخدام from للوصول إلى الجدول بشكل صحيح في الإصدار 3.x
        val books = supabaseClient.from("books")
            .select()
            .decodeList<Book>()
        emit(books)
    }.flowOn(Dispatchers.IO)

    override fun getBookByIsbn(isbn: String): Book? {
        // يمكن البحث في القائمة المحملة في الـ ViewModel أو تنفيذ استعلام سحابي هنا
        return null
    }

    override suspend fun addBook(book: Book, imageBytes: ByteArray?) {
        withContext(Dispatchers.IO) {
            var finalBook = book

            imageBytes?.let { bytes ->
                val fileName = "${book.isbn}_cover.jpg"
                val bucket = supabaseClient.storage.from("book_covers")

                // الرفع مع تفعيل خاصية التحديث (upsert)
                bucket.upload(path = fileName, data = bytes) {
                    upsert = true
                }

                val publicUrl = bucket.publicUrl(fileName)
                finalBook = book.copy(imageUrl = publicUrl)
            }

            // حفظ بيانات الكتاب في قاعدة البيانات
            supabaseClient.from("books").insert(finalBook)
        }
    }
}

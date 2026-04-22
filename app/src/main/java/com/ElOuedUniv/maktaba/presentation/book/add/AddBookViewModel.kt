package com.ElOuedUniv.maktaba.presentation.book.add

import androidx.lifecycle.ViewModel
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: AddBookUiAction) {
        when (action) {
            is AddBookUiAction.OnTitleChange -> _uiState.update { it.copy(title = action.title) }
            is AddBookUiAction.OnIsbnChange  -> _uiState.update { it.copy(isbn = action.isbn) }
            is AddBookUiAction.OnPagesChange -> _uiState.update { it.copy(nbPages = action.pages) }
            AddBookUiAction.OnAddClick       -> addBook()
        }
        // إعادة التحقق بعد كل تغيير
        _uiState.update { validateInputs(it) }
    }

    // Bonus — validateInputs
    private fun validateInputs(state: AddBookUiState): AddBookUiState {
        val titleError = if (state.title.isBlank()) "Title cannot be empty" else null
        val isbnError = when {
            state.isbn.isBlank()                  -> "ISBN cannot be empty"
            !state.isbn.matches(Regex("\\d{13}")) -> "ISBN must be exactly 13 digits"
            else -> null
        }
        val pagesError = when {
            state.nbPages.isBlank()             -> "Pages cannot be empty"
            state.nbPages.toIntOrNull() == null -> "Pages must be a number"
            state.nbPages.toInt() <= 0          -> "Pages must be a positive number"
            else -> null
        }
        return state.copy(
            titleError  = titleError,
            isbnError   = isbnError,
            pagesError  = pagesError,
            isFormValid = titleError == null && isbnError == null && pagesError == null
                    && state.title.isNotBlank() && state.isbn.isNotBlank() && state.nbPages.isNotBlank()
        )
    }

    private fun addBook() {
        val s = _uiState.value
        val validated = validateInputs(s)
        if (!validated.isFormValid) { _uiState.value = validated; return }
        val book = Book(isbn = s.isbn, title = s.title, nbPages = s.nbPages.toInt())
        addBookUseCase(book)
        _uiState.update { it.copy(isSuccess = true) }
    }
}
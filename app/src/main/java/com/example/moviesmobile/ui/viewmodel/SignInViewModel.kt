package com.example.moviesmobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.SignInRequest
import com.example.moviesmobile.data.entity.SignInResponse
import com.example.moviesmobile.data.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel that handles sign-in business logic and state management
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Initial)
    val signInState: StateFlow<SignInState> = _signInState

    // Form field update handlers
    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    // Handle sign-in process with validation
    fun onSignInClick() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                if (_email.value.isEmpty() || _password.value.isEmpty()) {
                    _signInState.value = SignInState.Error("Email and password cannot be empty")
                    return@launch
                }

                if (!_email.value.contains("@")) {
                    _signInState.value = SignInState.Error("Please enter a valid email")
                    return@launch
                }

                val response = authRepository.signIn(_email.value, _password.value)
                _signInState.value = SignInState.Success(response)
                Log.d("SignIn", "Success: ${response.email}")
            } catch (e: Exception) {
                Log.e("SignIn", "Error: ${e.message}", e)
                _signInState.value = SignInState.Error(e.message ?: "An error occurred")
            } finally {
                _isLoading.value = false
            }
        }
    }
}

// Sealed class representing different states of sign-in process
sealed class SignInState {
    object Initial : SignInState()
    data class Success(val response: SignInResponse) : SignInState()
    data class Error(val message: String) : SignInState()
}
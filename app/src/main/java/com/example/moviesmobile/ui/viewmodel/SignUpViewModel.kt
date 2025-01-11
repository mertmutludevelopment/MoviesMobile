package com.example.moviesmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.repo.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// States for the sign-up process
sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}

// ViewModel that handles user registration logic and state management
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: IAuthRepository
) : ViewModel() {
    
    // State flows for registration form fields
    private val _fullName = MutableStateFlow("")
    val fullName: StateFlow<String> = _fullName

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    // Form field update handlers
    fun onFullNameChange(newValue: String) {
        _fullName.value = newValue
    }

    fun onEmailChange(newValue: String) {
        _email.value = newValue
    }

    fun onPasswordChange(newValue: String) {
        _password.value = newValue
    }

    // Handle registration process
    fun onSignUpClick() {
        viewModelScope.launch {
            _isLoading.value = true
            _signUpState.value = SignUpState.Loading
            
            try {
                val response = authRepository.signUp(
                    fullName = fullName.value,
                    email = email.value,
                    password = password.value
                )
                _signUpState.value = SignUpState.Success
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(e.message ?: "An error occurred")
            } finally {
                _isLoading.value = false
            }
        }
    }
} 
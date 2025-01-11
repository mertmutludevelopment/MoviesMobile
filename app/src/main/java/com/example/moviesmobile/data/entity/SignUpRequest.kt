package com.example.moviesmobile.data.entity

// Data class for user registration request with required and default fields
data class SignUpRequest(
    val id: Int = 0,
    val fullName: String,
    val email: String,
    val password: String,
    val mobile: String = "string",
    val acceptTerms: Boolean = true,
    val acceptPrivacyPolicy: Boolean = true,
    val createdAt: String = "2025-01-11T11:20:07.078Z"
) 
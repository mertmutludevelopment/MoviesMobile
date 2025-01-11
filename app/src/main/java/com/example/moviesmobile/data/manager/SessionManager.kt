package com.example.moviesmobile.data.manager

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        const val PREFS_NAME = "MoviesMobilePrefs"
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    fun saveTokens(accessToken: String, refreshToken: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.putString(REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN, null)
    }

    fun clearSession() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return getAccessToken() != null
    }
} 
package com.febiarifin.stuntcare.util

import android.content.Context
import com.febiarifin.stuntcare.model.User

class UserPreference(context: Context) {
    companion object{
        private const val PREFERENCE_NAME = "user_preference"
        private const val NAME = "name"
        private const val USER_ID = "user_id"
        private const val TOKEN = "token"
        private const val EMAIL = "email"
    }

    private val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setUser(value: User){
        val editor = preferences.edit()
        editor.putString(NAME, value.name)
        editor.putString(USER_ID, value.id)
        editor.putString(TOKEN, value.token)
        editor.putString(EMAIL, value.email)
        editor.apply()
    }

    fun getUserId(): String?{
        return preferences.getString(USER_ID, null)
    }

    fun getUserName(): String?{
        return preferences.getString(NAME, null)
    }

    fun getUserEmail(): String?{
        return preferences.getString(EMAIL, null)
    }

    fun getUserToken(): String?{
        return preferences.getString(TOKEN, null)
    }

    fun clearPreference(){
        val editor = preferences.edit()
        editor.remove(NAME)
        editor.remove(USER_ID)
        editor.remove(TOKEN)
        editor.apply()
    }

}
package com.example.abc_test.business.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log

class SharedPreferences {

    private lateinit var sharedPreferences: SharedPreferences;

    fun setSharedPreferences(applicationContext: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }

    fun getFavoriteIds(): MutableSet<String> {
        val favIds = sharedPreferences.getStringSet(FAVORITE_KEY, null) ?: mutableSetOf<String>()
        Log.d("SharedPreferences", "favIds: ${favIds}")

        return favIds
    }

    fun addFavorite(id: String) {
        sharedPreferences.let {
            with(sharedPreferences.edit()) {
                clear();
                val set: MutableSet<String> = getFavoriteIds()
                set.add(id)
                Log.d("SharedPreferences", "addFavorite : ${set}")
                putStringSet(FAVORITE_KEY, set);
                apply()
            }
        }
    }

    fun removeFavorite(id: String) {
        sharedPreferences.let {
            with(sharedPreferences.edit()) {
                clear();
                val set: MutableSet<String> = getFavoriteIds()
                set.remove(id)
                putStringSet(FAVORITE_KEY, set);
                apply()
            }
        }
    }

    companion object {
        val FAVORITE_KEY = "FAVORITE_KEY"

        private var pref: com.example.abc_test.business.data.SharedPreferences? = null;

        fun getInstance(applicationContext: Context?): com.example.abc_test.business.data.SharedPreferences {
            if (pref == null && applicationContext != null) {
                pref = SharedPreferences()
                pref?.setSharedPreferences(applicationContext)
            }
            return pref!!
        }
    }
}
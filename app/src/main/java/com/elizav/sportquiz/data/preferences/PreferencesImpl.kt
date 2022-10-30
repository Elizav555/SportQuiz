package com.elizav.sportquiz.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.elizav.sportquiz.BuildConfig
import com.elizav.sportquiz.domain.preferences.Preferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : Preferences {

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val gson: Gson = Gson()

    init {
        sharedPreferences =
            context.getSharedPreferences(BuildConfig.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            editor = sharedPreferences?.edit()
        }
        editor?.apply()
    }

    override fun <T> getItem(key: String, type: Type): T? {
        val jsonText: String? =
            sharedPreferences?.getString(key, null)
        return gson.fromJson(jsonText, type)
    }

    override fun <T> setItem(key: String, item: T) {
        val jsonText: String = gson.toJson(item)
        editor?.putString(key, jsonText)
        editor?.apply()
    }

    override fun <T> deleteItem(key: String) {
        editor?.remove(key)
        editor?.apply()
    }
}
package com.example.investmenttodo.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.investmenttodo.dataclass.ProjectData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Prefs(context: Context) {

    companion object {
        private const val PREFS_FILENAME = "shared_prefs_name"

        private const val KEY_USER_NAME = "KEY_USER_NAME"
        private const val KEY_PROJECT_DATA = "KEY_PROJECT_DATA"

        private const val KEY_MY_BOOLEAN = "my_boolean"
        private const val KEY_MY_ARRAY = "string_array"
    }

    private val gson = Gson()
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var userName: String
        get() = sharedPrefs.getString(KEY_USER_NAME, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_USER_NAME, value) }

    var projectData: ProjectData
        get() {
            val jsonString = sharedPrefs.getString(KEY_PROJECT_DATA, null) ?: return ProjectData()
            return gson.fromJson(jsonString, object : TypeToken<ProjectData>() {}.type)
        }
        set(value) = sharedPrefs.edit { putString(KEY_PROJECT_DATA, gson.toJson(value)) }

    var myBoolean: Boolean
        get() = sharedPrefs.getBoolean(KEY_MY_BOOLEAN, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_MY_BOOLEAN, value) }

    var myStringArray: Array<String>
        get() = sharedPrefs.getStringSet(KEY_MY_ARRAY, emptySet())?.toTypedArray() ?: emptyArray()
        set(value) = sharedPrefs.edit { putStringSet(KEY_MY_ARRAY, value.toSet()) }
}
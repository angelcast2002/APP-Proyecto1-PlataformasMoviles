package com.example.fordogs.ui.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

const val PREFERENCES_NAME = "settings"
const val KEY = "isLogged"
const val VALUE = "TRUE"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

suspend fun DataStore<Preferences>.getPreferencesValue(): String?{
    val dataStoreKey = stringPreferencesKey(KEY)
    val preferences = data.first()
    return preferences[dataStoreKey]
}

suspend fun DataStore<Preferences>.savePreferencesValue(){
    val dataStoreKey = stringPreferencesKey(KEY)
    edit { settings ->
        settings[dataStoreKey] = VALUE
    }
}

suspend fun DataStore<Preferences>.removePreferencesValue(){
    val dataStoreKey = stringPreferencesKey(KEY)
    edit { settings ->
        settings.remove(dataStoreKey)
    }
}
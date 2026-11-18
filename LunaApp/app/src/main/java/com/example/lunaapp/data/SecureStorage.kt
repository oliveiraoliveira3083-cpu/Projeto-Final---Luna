package com.example.lunaapp.data

import android.content.Context
import android.content.SharedPreferences
import com.example.lunaapp.model.Contact
import org.json.JSONArray
import org.json.JSONObject

object SecureStorage {
    private const val PREF_NAME = "luna_secure_prefs"
    private const val KEY_PASSWORD = "key_app_password"
    private const val KEY_CONTACTS = "key_emergency_contacts"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getSavedPassword(context: Context): String? {
        return getPrefs(context).getString(KEY_PASSWORD, null)
    }

    fun savePassword(context: Context, password: String) {
        getPrefs(context).edit().putString(KEY_PASSWORD, password).apply()
    }

    fun getContacts(context: Context): List<Contact> {
        val jsonString = getPrefs(context).getString(KEY_CONTACTS, null) ?: return emptyList()
        val list = mutableListOf<Contact>()
        try {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                list.add(
                    Contact(
                        id = obj.optString("id", java.util.UUID.randomUUID().toString()),
                        name = obj.getString("name"),
                        phone = obj.getString("phone"),
                        relationship = obj.getString("relationship")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun saveContacts(context: Context, contacts: List<Contact>) {
        val jsonArray = JSONArray()
        for (c in contacts) {
            val obj = JSONObject().apply {
                put("id", c.id)
                put("name", c.name)
                put("phone", c.phone)
                put("relationship", c.relationship)
            }
            jsonArray.put(obj)
        }
        getPrefs(context).edit().putString(KEY_CONTACTS, jsonArray.toString()).apply()
    }

    fun addContact(context: Context, contact: Contact) {
        val current = getContacts(context).toMutableList()
        current.add(contact)
        saveContacts(context, current)
    }

    fun deleteContact(context: Context, contactId: String) {
        val current = getContacts(context).filterNot { it.id == contactId }
        saveContacts(context, current)
    }
}

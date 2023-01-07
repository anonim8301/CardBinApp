package com.example.cardbinapp.data.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.cardbinapp.domain.model.Bank
import com.example.cardbinapp.domain.model.Country
import com.example.cardbinapp.domain.model.Number
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@TypeConverters
class TypeConverters {

    private val separator = ","

    @TypeConverter
    fun fromBankToString(value: Bank?): String {
        val gson = Gson()
        val type = object : TypeToken<Bank>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toBankFromString(value: String): Bank? {
        val gson = Gson()
        val type = object : TypeToken<Bank>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCountryToString(value: Country?): String {
        val gson = Gson()
        val type = object : TypeToken<Country>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryFromString(value: String): Country? {
        val gson = Gson()
        val type = object : TypeToken<Country>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromNumberToString(value: Number?): String {
        val gson = Gson()
        val type = object : TypeToken<Number>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringFromNumber(value: String): Number? {
        val gson = Gson()
        val type = object : TypeToken<Number>() {}.type
        return gson.fromJson(value, type)
    }
}


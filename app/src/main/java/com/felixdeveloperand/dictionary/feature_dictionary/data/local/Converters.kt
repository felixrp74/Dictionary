package com.felixdeveloperand.dictionary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.felixdeveloperand.dictionary.feature_dictionary.data.util.GsonParser
import com.felixdeveloperand.dictionary.feature_dictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: GsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json:String):List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings:List<Meaning>):String{
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }
}
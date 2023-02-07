package co.nisum.basicpokedex.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import co.nisum.basicpokedex.data.remote.responses.Abilities
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

@ProvidedTypeConverter
class Converters {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<Abilities?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Abilities?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Abilities?>?): String? {
        return gson.toJson(someObjects)
    }

}
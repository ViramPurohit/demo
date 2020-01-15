package shaadi.com.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class Converters {
    @TypeConverter
    fun fromString(value: String): Any? {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson<Any>(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}
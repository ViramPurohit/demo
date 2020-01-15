package shaadi.com.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [
        ShaadiUsers :: class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ShaadiDb : RoomDatabase() {

    abstract fun shaadiUserDao() : ShaadiUserDao
}
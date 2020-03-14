package com.asama.luong.goldpricemvvm.data.db.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asama.luong.goldpricemvvm.data.db.MetalsPriceDao
import org.kodein.di.Volatile

@Database(
    entities = [MetalsPriceDao::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun metalsPriceDao(): MetalsPriceDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "metals.db").build()
    }
}
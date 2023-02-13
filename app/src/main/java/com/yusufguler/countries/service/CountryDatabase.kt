package com.yusufguler.countries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusufguler.countries.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase  : RoomDatabase() {

    abstract fun countryDao() : CountryDao
    //Singleton

    //app'in herhangi bir yerinden ulaşabilmek için companinon object şeklinde tanımla singletonu

    companion object{
        //farklı threadlere de görünür hale gelir @Volatile ile
        @Volatile private var instance : CountryDatabase? = null
        private val lock = Any()
        //synchronized yapmanın sebebi aynı anda başka bir threadden istenirse çakışma yaşanmaması için
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context : Context)= Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()
    }

}
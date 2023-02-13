package com.yusufguler.countries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yusufguler.countries.model.Country
@Dao
interface CountryDao {
    //data access object
    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>
    //suspendfun  for -> coroutines , pauseable & resumeable
    //vararg -> multiple country objects
    // List'll return primary keys
    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid= :countryId") //for detailFragment
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}
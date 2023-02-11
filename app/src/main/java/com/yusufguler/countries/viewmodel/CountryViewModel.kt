package com.yusufguler.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufguler.countries.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com")
        countryLiveData.value = country
    }

}
package com.yusufguler.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufguler.countries.model.Country

class FeedViewModel : ViewModel() {
    val countries  = MutableLiveData<List<Country>>()
    val countryError =  MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com")
        val country1 = Country("France","Europe","Paris","EUR","French","www.ss.com")
        val country2 = Country("Germany","Europe","Berlin","EUR","German","www.ss.com")
        val countryList = arrayListOf<Country>(country,country1,country2)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

}
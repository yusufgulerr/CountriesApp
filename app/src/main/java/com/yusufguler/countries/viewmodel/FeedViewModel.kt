package com.yusufguler.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufguler.countries.model.Country

class FeedViewModel : ViewModel() {
    val countries  = MutableLiveData<List<Country>>()
    val countryError =  MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()



}
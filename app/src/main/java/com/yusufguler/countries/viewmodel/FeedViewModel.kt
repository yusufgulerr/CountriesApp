package com.yusufguler.countries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufguler.countries.model.Country
import com.yusufguler.countries.service.CountryAPIService
import com.yusufguler.countries.service.CountryDatabase
import com.yusufguler.countries.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val disposalbe = CompositeDisposable()
    private val customPreferences = CustomSharedPreferences(getApplication())


    val countries  = MutableLiveData<List<Country>>()
    val countryError =  MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        countryLoading.value=true

        disposalbe.add(
         countryAPIService.getData()
             .subscribeOn(Schedulers.newThread())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                 override fun onSuccess(t: List<Country>) {
                    storeInSQLite(t)
                 }

                 override fun onError(e: Throwable) {
                    countryLoading.value=false
                     countryError.value=true
                     e.printStackTrace()
                 }
             })
        )
    }
        private fun showCountries(countryList : List<Country>){
            countries.value = countryList
            countryError.value=false
            countryLoading.value= false
        }
        private fun storeInSQLite(list : List<Country>){
            launch {
                val dao = CountryDatabase(getApplication()).countryDao()
                dao.deleteAllCountries()
                val listLong =  dao.insertAll(*list.toTypedArray()) //list -> individual
                var i = 0
                while(i<list.size){
                    list[i].uuid  = listLong[i].toInt()
                    i = i + 1
                }
                showCountries(list)
            }
            //nanoTime en hassas zaman birimi
            customPreferences.saveTime(System.nanoTime())
        }
}
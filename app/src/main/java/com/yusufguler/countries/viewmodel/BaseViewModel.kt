package com.yusufguler.countries.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {

    //coroutine'in ne yapacağını söylememiz gerekiyor
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // işini yap daha sonra main threade geri dön anlamına gelir

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
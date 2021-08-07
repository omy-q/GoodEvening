package com.example.goodevening.superview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodevening.domainmodel.model.Facade
import com.example.goodevening.domainmodel.model.FacadeImpl
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    val facade: Facade = FacadeImpl()
) : ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getFilm() = getDataFromLocalSource()


    private fun getDataFromLocalSource() {
        when ((1..2).random()) {
            1 -> {
                liveDataObserver.postValue(AppState.Loading)
                sleep(3000)
            }
            2 -> {liveDataObserver.postValue(AppState.Success(facade.getLocalData())) }
        }
//        Thread {
//            liveDataObserver.postValue(AppState.Loading)
//            sleep(2000)
//            liveDataObserver.postValue(AppState.Success(facade.getLocalData()))
//        }.start()
    }
}
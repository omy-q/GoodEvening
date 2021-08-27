package com.example.goodevening.superview.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.FilmLoader
import com.example.goodevening.domainmodel.model.Facade
import com.example.goodevening.domainmodel.model.FacadeImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val facade: Facade = FacadeImpl()) : ViewModel(){

    private val listener: Facade.FilmLoaderListener = object : Facade.FilmLoaderListener{
        override fun onLoaded(categoriesFilm : List<CategoryFilm>) {
            liveDataObserver.postValue(AppState.Success(categoriesFilm))
        }
        override fun onFailed(throwable: Throwable) {
            liveDataObserver.postValue(AppState.Error(throwable))
        }
    }

    init{
        facade.setListener(listener)
    }

    fun getLiveData() = liveDataObserver

    fun getFilm() = getDataFromServer()

    private fun getDataFromLocalSource() {
        Thread {
            liveDataObserver.postValue(AppState.Loading)
            sleep(2000)
            liveDataObserver.postValue(AppState.Success(facade.getLocalData()))
        }.start()
    }

    private fun getDataFromServer() {
        liveDataObserver.postValue(AppState.Loading)
        sleep(2000)
        facade.getServerData()

    }
}
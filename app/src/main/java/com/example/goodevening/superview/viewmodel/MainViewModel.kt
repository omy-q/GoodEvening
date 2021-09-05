package com.example.goodevening.superview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodevening.app.App.Companion.getDB
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.model.CallbackDB
import com.example.goodevening.domainmodel.moviedb.FilmDTO
import com.example.goodevening.domainmodel.moviedb.RemoteDataSource
import com.example.goodevening.domainmodel.model.Facade
import com.example.goodevening.domainmodel.model.FacadeImpl
import com.example.goodevening.domainmodel.room.facade.RoomFacadeImpl
import com.example.goodevening.domainmodel.utils.convertDTOtoModel
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call
import java.lang.Thread.sleep


private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val facade: Facade = FacadeImpl(RemoteDataSource(), RoomFacadeImpl(getDB()))
) : ViewModel() {

    private var categoriesList : MutableList<CategoryFilm> = mutableListOf()

    fun getLiveData() = liveDataObserver

    fun getFilm() = getData()

    private fun getData(){
        categoriesList.clear()
        liveDataObserver.postValue(AppState.Loading)
        getDataFromRemoteSource()
        getDataFromLocalSource()
        liveDataObserver.postValue(AppState.Success(categoriesList))
    }

    private fun getDataFromRemoteSource() {
        facade.getServerData(callbackAPI)
    }

    private val callbackAPI = object : Callback<FilmDTO> {

        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                categoriesList.add(convertDTOtoModel(serverResponse))
                liveDataObserver.postValue(AppState.Success(categoriesList))
            } else {
                liveDataObserver.postValue(AppState.Error(Throwable(SERVER_ERROR)))
            }
        }
        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            liveDataObserver.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    private fun getDataFromLocalSource() {
        facade.getLocalData(callbackDB)
    }

    private val callbackDB = object : CallbackDB {
        override fun onResponse(result: CategoryFilm) {
            if(result.films.isNotEmpty()){
                categoriesList.add(result)
                liveDataObserver.postValue(AppState.Success(categoriesList))
            }
        }
    }

    fun saveRecentFilmToDB(film : Film) {
        facade.saveRecentFilm(film)
    }
}
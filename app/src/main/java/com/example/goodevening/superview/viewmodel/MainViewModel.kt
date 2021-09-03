package com.example.goodevening.superview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodevening.app.App.Companion.getDB
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
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

    private val callBack = object : Callback<FilmDTO> {

        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    liveDataObserver.postValue(AppState.Error(Throwable(SERVER_ERROR)))
                }
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            liveDataObserver.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: FilmDTO) {
            facade.savePopularFilms(convertDTOtoModel(serverResponse))
//            getPopularData()
//            categoriesList.add(convertDTOtoModel(serverResponse))
//            liveDataObserver.postValue(AppState.Success(categoriesList))

        }
    }

    private fun getDataFromServer() {
        facade.getServerData(callBack)
    }

    private fun getRecentData(){
        if (facade.getDBRecentFilms().films.isNotEmpty()){
            categoriesList.add(facade.getDBRecentFilms())
            liveDataObserver.postValue(AppState.Success(categoriesList))
        }
    }

    private fun getFavoriteData(){
        if (facade.getDBFavoriteFilms().films.isNotEmpty()){
            categoriesList.add(facade.getDBFavoriteFilms())
            liveDataObserver.postValue(AppState.Success(categoriesList))
        }
    }

    private fun getWatchedData(){
        if (facade.getDBWatchedFilms().films.isNotEmpty()){
            categoriesList.add(facade.getDBWatchedFilms())
            liveDataObserver.postValue(AppState.Success(categoriesList))
        }
    }

    private fun getWillWatchData(){
        if (facade.getDBWillWatchFilms().films.isNotEmpty()){
            categoriesList.add(facade.getDBWillWatchFilms())
            liveDataObserver.postValue(AppState.Success(categoriesList))
        }
    }

    private fun getPopularData(){
        if (facade.getDBPopularFilms().films.isNotEmpty()){
            categoriesList.add(facade.getDBPopularFilms())
            liveDataObserver.postValue(AppState.Success(categoriesList))
        }
    }

    private fun getData(){
        categoriesList.clear()
        liveDataObserver.postValue(AppState.Loading)
        getDataFromServer()
        getRecentData()
        getFavoriteData()
        getPopularData()
        getWatchedData()
        getWillWatchData()
    }

    fun saveRecentFilmToDB(film : Film) {
        facade.saveRecentFilm(film)
    }

    fun saveFavoriteFilmToDB(film : Film) {
        facade.saveFavoriteFilm(film)
    }

    fun saveWatchedFilmToDB(film : Film) {
        facade.saveWatchedFilm(film)
    }

    fun saveWillWatchedFilmToDB(film : Film) {
        facade.saveWillWatchFilm(film)
    }

//    fun savePopularFilmToDB(films: List<Film>) {
//        facade.savePopularFilms(films)
//    }


}
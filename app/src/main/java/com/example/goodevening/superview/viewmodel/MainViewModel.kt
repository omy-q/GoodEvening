package com.example.goodevening.superview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodevening.app.App.Companion.getDB
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.genres
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


private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val facade: Facade = FacadeImpl(RemoteDataSource(), RoomFacadeImpl(getDB()))
) : ViewModel() {

    private var categoriesList : MutableList<CategoryFilm> = mutableListOf()
    private var menuCategoriesList: MutableMap<String, CategoryFilm> = mutableMapOf()
    private val GENRES = "with_genres"
    private val POPULAR = "Popular"

    fun getLiveData() = liveDataObserver

    fun getFilm() = getData()

    private fun getData(){
        categoriesList.clear()
        liveDataObserver.postValue(AppState.Loading)
        getDataFromRemoteSource()
        getDataFromLocalSource()
    }

    private fun getDataFromRemoteSource() {
        facade.getServerData(callbackAPI)
    }

    private val callbackAPI = object : Callback<FilmDTO> {

        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                val category = getCategory(response)
                categoriesList.add(convertDTOtoModel(category, serverResponse))
                liveDataObserver.postValue(AppState.Success(categoriesList, menuCategoriesList))
            } else {
                liveDataObserver.postValue(AppState.Error(Throwable(SERVER_ERROR)))
            }
        }
        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            liveDataObserver.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun getCategory(response: Response<FilmDTO>) : String{
            return if (response.raw().request().url().queryParameterValues(GENRES).size > 0) {
                genres[Integer.parseInt(response.raw().request().url().queryParameterValues(GENRES)[0])]?:"Default"
            } else POPULAR
        }
    }

    private fun getDataFromLocalSource() {
        facade.getLocalData(callbackDB)
    }

    private val callbackDB = object : CallbackDB {
        override fun onResponse(result: CategoryFilm) {
            if(result.films.isNotEmpty()){
                categoriesList.add(result)
                when(result.category){
                    "Favorite films" -> {
                        menuCategoriesList["favorite"] = result
                    }
                    "Watched films" -> {
                        menuCategoriesList["watched"] = result
                    }
                    "Will watched films" -> {
                        menuCategoriesList["willWatch"] = result
                    }
                }
                liveDataObserver.postValue(AppState.Success(categoriesList, menuCategoriesList))
            }
        }
    }

    fun saveRecentFilmToDB(film : Film) {
        facade.saveRecentFilm(film)
    }
}
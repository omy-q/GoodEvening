package com.example.goodevening.superview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodevening.domainmodel.FilmDTO
import com.example.goodevening.domainmodel.RemoteDataSource
import com.example.goodevening.domainmodel.model.Facade
import com.example.goodevening.domainmodel.model.FacadeImpl
import com.example.goodevening.domainmodel.utils.convertDTOtoModel
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call
import java.lang.Thread.sleep


private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val facade: Facade = FacadeImpl(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = liveDataObserver

    fun getFilm() = getDataFromServer()

    private fun getDataFromLocalSource() {
        Thread {
            liveDataObserver.postValue(AppState.Loading)
            sleep(2000)
            liveDataObserver.postValue(AppState.Success(facade.getLocalData()))
        }.start()
    }

    private val callBack = object : Callback<FilmDTO> {

        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            liveDataObserver.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            liveDataObserver.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: FilmDTO): AppState {
            return AppState.Success(convertDTOtoModel(serverResponse))
        }
    }

    private fun getDataFromServer() {
        liveDataObserver.postValue(AppState.Loading)
        facade.getServerData(callBack)
    }
}
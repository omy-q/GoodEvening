package com.example.goodevening.superview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.goodevening.R
import com.example.goodevening.databinding.ActivityMainBinding
import com.example.goodevening.databinding.MainFragmentBinding
import com.example.goodevening.superview.viewmodel.AppState
import com.example.goodevening.superview.viewmodel.MainViewModel

class MainFragment : Fragment() {
    lateinit var viewModel:MainViewModel
    lateinit var binding: MainFragmentBinding
    companion object {
        fun newInstance()= MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {renderData(it)})
        viewModel.getFilm()
    }

    private fun renderData(appState: AppState) {
        when(appState){
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                setData(appState)
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
                Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setData(appState: AppState.Success) {
        binding.filmPoster.setImageResource(R.drawable.film_test)
        binding.filmName.text = appState.dataFilm.name
        binding.filmYear.text = appState.dataFilm.year
        binding.filmCountry.text = appState.dataFilm.country

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}
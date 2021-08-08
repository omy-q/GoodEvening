package com.example.goodevening.superview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.goodevening.R
import com.example.goodevening.databinding.ActivityMainBinding
import com.example.goodevening.databinding.MainFragmentBinding
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.superview.viewmodel.AppState
import com.example.goodevening.superview.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private val adapter = FilmAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.fragment_container, FilmFragment.newInstance(Bundle().apply {
                        putParcelable(FilmFragment.BUNDLE_EXTRA, film)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
            getFilm()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
            is AppState.Success -> {
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    RecyclerView.layoutManager = GridLayoutManager(context, 3)
                    RecyclerView.adapter = adapter
                    adapter.setFilm(appState.filmData)
//                    root.showMessageByID(R.string.success, Snackbar.LENGTH_LONG)
                }
            }
            AppState.Loading -> {
                with(binding) {
                    loadingLayout.visibility = View.VISIBLE
//                    root.showMessageByText("Loading", Snackbar.LENGTH_LONG)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeListener()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }
}
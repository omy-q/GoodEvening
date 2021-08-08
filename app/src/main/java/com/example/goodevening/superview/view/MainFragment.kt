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
    private lateinit var viewModel: MainViewModel
    private val adapter = FilmAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(FilmFragment.BUNDLE_EXTRA, film)
                manager.beginTransaction()
                    .replace(R.id.fragment_container, FilmFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    private var _binding: MainFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {renderData(it)})
        viewModel.getFilm()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val recycler = binding.RecyclerView
        recycler.layoutManager = GridLayoutManager(context, 3)
        recycler.adapter = adapter
        return binding.root
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                adapter.setFilm(appState.filmData)
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
                Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
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
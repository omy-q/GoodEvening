package com.example.goodevening.superview.view.mainview

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goodevening.R
import com.example.goodevening.databinding.MainFragmentBinding
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.superview.view.additionalview.CategoryFilmFragment
import com.example.goodevening.superview.view.detailsview.FilmFragment
import com.example.goodevening.superview.view.utils.showMessageByText
import com.example.goodevening.superview.viewmodel.AppState
import com.example.goodevening.superview.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val parentAdapter = FilmParentAdapter(
        object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            viewModel.saveRecentFilmToDB(film)
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.fragment_container, FilmFragment
                        .newInstance(Bundle().apply {
                            putParcelable(FilmFragment.BUNDLE_EXTRA, film)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }, object : OnMoreButtonClickListener{
            override fun onMoreButtonClick(category: CategoryFilm) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .replace(R.id.fragment_container, CategoryFilmFragment
                            .newInstance(Bundle().apply {
                                putParcelable(CategoryFilmFragment.BUNDLE_EXTRA, category)
                            }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        })

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var switchAdult: SwitchCompat? = null

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
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    root.showMessageByText("Error", Snackbar.LENGTH_LONG)
                }
            }
            is AppState.Success -> {
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    RecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    RecyclerView.adapter = parentAdapter
                    parentAdapter.setData(appState.filmData)
                    root.showMessageByText("Success", Snackbar.LENGTH_LONG)
                }
            }
            AppState.Loading -> {
                with(binding) {
                    loadingLayout.visibility = View.VISIBLE
                    root.showMessageByText("Loading", Snackbar.LENGTH_LONG)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        parentAdapter.removeListener()
    }

//    private fun View.showMessageByID(resourceID: Int, duration: Int) {
//        Snackbar.make(this, requireActivity().resources.getString(resourceID), duration).show()
//    }
//
//    private fun View.showMessageByText(text: String, duration: Int) {
//        Snackbar.make(this, text, duration).show()
//    }
}
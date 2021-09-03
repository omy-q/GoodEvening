package com.example.goodevening.superview.view.additionalview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.goodevening.R
import com.example.goodevening.databinding.CategoryFilmFragmentBinding
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.superview.view.detailsview.FilmFragment
import com.example.goodevening.superview.view.mainview.OnItemViewClickListener

class CategoryFilmFragment : Fragment() {

    private var categoryFilms : CategoryFilm? = null

    private val categoryAdapter = CategoryAdapter(object : OnItemViewClickListener{
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

    private var _binding : CategoryFilmFragmentBinding? = null
    private val binding get() = _binding!!

    companion object{
        const val BUNDLE_EXTRA = "films"
        fun newInstance(bundle: Bundle): CategoryFilmFragment {
            val fragment = CategoryFilmFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = CategoryFilmFragmentBinding.inflate(inflater, container, false)
        categoryFilms = arguments?.getParcelable(BUNDLE_EXTRA) as? CategoryFilm
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(binding){
            category.text = categoryFilms?.category
            categoryRecyclerView.layoutManager = GridLayoutManager(context, 3)
            categoryRecyclerView.adapter = categoryAdapter
            categoryFilms?.let { categoryAdapter.setData(it.films) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        categoryAdapter.removeListener()
    }
}
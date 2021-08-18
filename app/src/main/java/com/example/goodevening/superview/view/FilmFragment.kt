package com.example.goodevening.superview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goodevening.R
import com.example.goodevening.databinding.FilmFragmentBinding
import com.example.goodevening.databinding.MainFragmentBinding
import com.example.goodevening.domainmodel.Film
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.film_description.view.*

class FilmFragment : Fragment() {

    private var _binding: FilmFragmentBinding? = null
    private val binding get() = _binding!!
    private val genresAdapter = GenresAdapter()

    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInstance(bundle: Bundle): FilmFragment {
            val fragment = FilmFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FilmFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let{film -> setData(film)}
    }

    private fun setData(film : Film){
        with(binding) {
            filmName.text = film.name
            filmRating.text = film.average.toString()
            filmTime.text = "2.15"
            filmYear.text = film.year
            filmCountry.text = "US"
            Picasso
                .get()
                .load(film.poster)
                .into(filmDescription.poster)
            filmDescription.description.text = film.description
            filmDescription.RecyclerGenres.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            filmDescription.RecyclerGenres.adapter = genresAdapter
            genresAdapter.setData(film.genres)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

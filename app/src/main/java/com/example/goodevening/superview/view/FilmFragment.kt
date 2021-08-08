package com.example.goodevening.superview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goodevening.R
import com.example.goodevening.databinding.FilmFragmentBinding
import com.example.goodevening.databinding.MainFragmentBinding
import com.example.goodevening.domainmodel.Film

class FilmFragment : Fragment() {

    private var _binding: FilmFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInstance(bundle : Bundle) : FilmFragment {
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
        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)
        if (film != null) {
            binding.filmName.text = film.name
            binding.filmRating.text = "5"
            binding.filmTime.text = "2.15"
            binding.filmYear.text = film.year
            binding.filmCountry.text = film.country
            binding.filmDescription.poster.setImageResource(R.drawable.film_test)
            binding.filmDescription.description.text = "DEFAULT"
        }
    }
}
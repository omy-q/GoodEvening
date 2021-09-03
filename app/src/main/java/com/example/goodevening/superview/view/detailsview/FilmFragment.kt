package com.example.goodevening.superview.view.detailsview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.example.goodevening.databinding.FilmFragmentBinding
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.superview.viewmodel.DetailsViewModel
import com.example.goodevening.superview.viewmodel.MainViewModel
import com.squareup.picasso.Picasso

class FilmFragment : Fragment() {

    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this).get(DetailsViewModel::class.java) }
    private var _binding: FilmFragmentBinding? = null
    private val binding get() = _binding!!
    private val genresAdapter = GenresAdapter()
    private var film: Film? = null

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
        film = arguments?.getParcelable<Film>(BUNDLE_EXTRA) as? Film
        film?.let{setData(it)}
        film?.let{initOptions(it)}
    }

    private fun initOptions(film: Film) {
       binding.favoriteFilm.setOnClickListener(View.OnClickListener {
           Toast.makeText(context, "Favorite", Toast.LENGTH_LONG).show()
           viewModel.saveFavoriteFilmToDB(film)
       })
        binding.watchedFilm.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "Watched", Toast.LENGTH_LONG).show()
            viewModel.saveWatchedFilmToDB(film)
        })
        binding.watchLaterFilm.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "Will watch", Toast.LENGTH_LONG).show()
            viewModel.saveWillWatchedFilmToDB(film)
        })

        binding.shareFilm.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "share", Toast.LENGTH_LONG).show()
            val sendIntent : Intent = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "I advise you to look the film: ${film.name}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        })
    }

    private fun setData(film : Film){
        with(binding) {
            filmName.text = film.name
            filmRating.text = film.average.toString()
            filmTime.text = "2.15"
            filmYear.text = film.year
            filmCountry.text = "US"
            filmDescription.poster.load(film.poster)
//            Picasso
//                .get()
//                .load(film.poster)
//                .into(filmDescription.poster)
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

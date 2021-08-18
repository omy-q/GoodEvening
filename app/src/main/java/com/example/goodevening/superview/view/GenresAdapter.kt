package com.example.goodevening.superview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.goodevening.R
import com.example.goodevening.domainmodel.Film
import com.squareup.picasso.Picasso

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {

    private lateinit var genres: List<String>

    fun setData(data: List<String>) {
        genres = data
        notifyDataSetChanged()
    }

    inner class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(genre : String) {
            with(itemView) {
                findViewById<TextView>(R.id.genre).text = genre
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapter.GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.genre_item_view, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: GenresAdapter.GenreViewHolder, position: Int) {
        holder.init(genres[position])
    }

    override fun getItemCount() = genres.size
}
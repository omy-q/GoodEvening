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

class FilmAdapter(private var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private var filmData: List<Film> = listOf()

    fun setFilm(data: List<Film>) {
        filmData = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var posterImageView: ImageView? = null
        private var nameTextView: TextView = itemView.findViewById(R.id.film_name_card_view)
        private var yearTextView: TextView = itemView.findViewById(R.id.film_year_card_view)
        private var countryTextView: TextView = itemView.findViewById(R.id.film_country_card_view)

        fun bind(film:Film) {
            posterImageView = itemView.findViewById(R.id.film_poster_card_view)
            nameTextView.text = film.name
            yearTextView.text = film.year
            countryTextView.text = film.country
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Item_Card_View", Toast.LENGTH_LONG).show()
                onItemViewClickListener?.onItemViewClick(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_item_view, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.bind(filmData[position])
    }

    override fun getItemCount(): Int = filmData.size
}

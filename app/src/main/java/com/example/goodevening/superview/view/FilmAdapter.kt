package com.example.goodevening.superview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.goodevening.R
import com.example.goodevening.domainmodel.Film
import org.w3c.dom.Text

class FilmAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private lateinit var filmData: List<Film>

    fun setFilm(data: List<Film>) {
        filmData = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun init(film: Film) {
            with(itemView) {
                findViewById<ImageView>(R.id.film_poster_card_view).setImageResource(R.drawable.film_test)
                findViewById<TextView>(R.id.film_name_card_view).text = film.name
                findViewById<TextView>(R.id.film_year_card_view).text = film.year
                findViewById<TextView>(R.id.film_country_card_view).text = film.country
                setOnClickListener {
                    Toast.makeText(itemView.context, "Item_Card_View", Toast.LENGTH_LONG).show()
                    onItemViewClickListener?.onItemViewClick(film)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_film_layout, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.init(filmData[position])
    }

    override fun getItemCount() = filmData.size
}

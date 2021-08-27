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
import org.w3c.dom.Text

class FilmAdapter(private var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private lateinit var films: List<Film>

    fun setFilm(data: List<Film>) {
        films = data
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
                findViewById<TextView>(R.id.film_country_card_view).text = "US"
                setOnClickListener {
                    Toast.makeText(itemView.context, "Item_Card_View", Toast.LENGTH_LONG).show()
                    onItemViewClickListener?.onItemViewClick(film)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_item_view, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.init(films[position])
    }

    override fun getItemCount() = films.size
}

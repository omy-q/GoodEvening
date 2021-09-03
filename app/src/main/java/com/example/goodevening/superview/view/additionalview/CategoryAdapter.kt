package com.example.goodevening.superview.view.additionalview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.goodevening.R
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.superview.view.mainview.FilmAdapter
import com.example.goodevening.superview.view.mainview.OnItemViewClickListener

class CategoryAdapter(private var onItemViewClickListener: OnItemViewClickListener?):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    private lateinit var films : List<Film>

    fun setData(data : List<Film>){
        films = data
        notifyDataSetChanged()
    }

    fun removeListener(){
        onItemViewClickListener = null
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bind(film : Film){
            with(itemView){
                findViewById<ImageView>(R.id.film_poster_card_view).load(film.poster)
                findViewById<TextView>(R.id.film_name_card_view).text = film.name
                findViewById<TextView>(R.id.film_year_card_view).text = film.year
                findViewById<TextView>(R.id.film_country_card_view).text = "US"
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(film)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.child_item_view, parent, false) as View)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount() = films.size
}
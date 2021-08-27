package com.example.goodevening.superview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodevening.R
import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film

class FilmParentAdapter(onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<FilmParentAdapter.ParentViewHolder>() {

    private var childAdapter: FilmAdapter = FilmAdapter(onItemViewClickListener)
    private lateinit var filmData: List<CategoryFilm>

    fun setData(data: List<CategoryFilm>) {
        filmData = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        childAdapter.removeListener()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmParentAdapter.ParentViewHolder {
        return ParentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_item_view, parent, false) as View
        )
    }

    inner class ParentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var childRecyclerView: RecyclerView

        fun init(filmCategory: CategoryFilm) {
            with(itemView) {
                findViewById<TextView>(R.id.test).text = filmCategory.category
                childRecyclerView = findViewById(R.id.RecyclerViewFilm)
            }
            with(childRecyclerView) {
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = childAdapter
                hasFixedSize()
                childAdapter.setFilm(filmCategory.films)
            }
        }
    }

    override fun onBindViewHolder(holder: FilmParentAdapter.ParentViewHolder, position: Int) {
        holder.init(filmData[position])
    }

    override fun getItemCount() = filmData.size

}
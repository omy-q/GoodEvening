package com.example.goodevening.superview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodevening.R
import com.example.goodevening.domainmodel.CategoryFilm

class FilmParentAdapter(private val onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<FilmParentAdapter.ParentViewHolder>() {

    private lateinit var childAdapters: MutableList<FilmAdapter>
    private lateinit var filmData: List<CategoryFilm>

    fun setData(data: List<CategoryFilm>) {
        childAdapters = mutableListOf(FilmAdapter(onItemViewClickListener), FilmAdapter(onItemViewClickListener))
        filmData = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        for (childAdapter in childAdapters) {
            childAdapter.removeListener()
        }
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

        fun init(filmCategory: CategoryFilm, position: Int) {
            with(itemView) {
                findViewById<TextView>(R.id.test).text = filmCategory.category
                childRecyclerView = findViewById(R.id.RecyclerViewFilm)
            }
            with(childRecyclerView) {
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = childAdapters[position]
                hasFixedSize()
                childAdapters[position].setFilm(filmCategory.films)
            }
        }
    }

    override fun onBindViewHolder(holder: FilmParentAdapter.ParentViewHolder, position: Int) {
        holder.init(filmData[position], position)
    }

    override fun getItemCount() = filmData.size

}
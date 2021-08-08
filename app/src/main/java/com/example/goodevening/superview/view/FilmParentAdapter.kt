package com.example.goodevening.superview.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodevening.R
import com.example.goodevening.domainmodel.Film

class FilmParentAdapter : RecyclerView.Adapter<FilmParentAdapter.ParentViewHolder>() {

    private var childAdapter : FilmAdapter = FilmAdapter()

    private var testVar : List<Film>  = listOf()

    fun setTestMethod(data: List<Film>) {
        testVar = data
//        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmParentAdapter.ParentViewHolder {
        return ParentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.parent_item_view, parent, false) as View)
    }

    inner class ParentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var categoryTextView : TextView = itemView.findViewById(R.id.test)
        private var childRecyclerView : RecyclerView = itemView.findViewById(R.id.RecyclerViewFilm)

        fun bind(films : List<Film>, position: Int) {
            categoryTextView.text = "category $position"
            childRecyclerView = itemView.findViewById(R.id.RecyclerViewFilm)
            childRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            childRecyclerView.adapter = childAdapter
            childRecyclerView.hasFixedSize()
            childAdapter.setFilm(films)

        }
    }

    override fun onBindViewHolder(holder: FilmParentAdapter.ParentViewHolder, position: Int) {
        holder.bind(testVar, position)
    }

    override fun getItemCount() = 10

}
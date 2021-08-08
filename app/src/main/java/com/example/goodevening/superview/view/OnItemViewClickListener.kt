package com.example.goodevening.superview.view

import com.example.goodevening.domainmodel.Film

interface OnItemViewClickListener {
    fun onItemViewClick(film: Film)
}
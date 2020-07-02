package com.example.myapplication.ui

public interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}
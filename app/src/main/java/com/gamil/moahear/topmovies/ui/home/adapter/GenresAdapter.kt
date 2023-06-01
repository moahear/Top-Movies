package com.gamil.moahear.topmovies.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gamil.moahear.topmovies.databinding.ItemHomeGenreBinding
import com.gamil.moahear.topmovies.model.home.ResponseGenres
import javax.inject.Inject

class GenresAdapter @Inject constructor() :
    RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val genres = ArrayList<ResponseGenres.ResponseGenresItem>()

    inner class GenresViewHolder(private val binding: ItemHomeGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindTopMovie(genre: ResponseGenres.ResponseGenresItem) {
            binding.apply {
                   txtName.text=genre.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding =
            ItemHomeGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bindTopMovie(genres[position])
    }

    private class TopMoviesDiffUtilCallBack(
        val oldList: List<ResponseGenres.ResponseGenresItem>,
        val newList: List<ResponseGenres.ResponseGenresItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }
    }

    fun submitList(newList: List<ResponseGenres.ResponseGenresItem>) {
        val diffResult = DiffUtil.calculateDiff(TopMoviesDiffUtilCallBack(genres, newList))
        diffResult.dispatchUpdatesTo(this)
        genres.clear()
        genres.addAll(newList)
    }
}
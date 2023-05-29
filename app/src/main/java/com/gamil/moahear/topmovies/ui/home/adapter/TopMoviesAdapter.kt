package com.gamil.moahear.topmovies.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gamil.moahear.topmovies.databinding.ItemHomeTopMovieBinding
import com.gamil.moahear.topmovies.model.home.ResponseMovies
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    private val topMovies = ArrayList<ResponseMovies.Data>()

    inner class TopMoviesViewHolder(private val binding: ItemHomeTopMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindTopMovie(topMovie: ResponseMovies.Data) {
            binding.apply {
                txtMovieName.text = topMovie.title
                txtMovieInfo.text =
                    "${topMovie.imdbRating} | ${topMovie.country} | ${topMovie.year}"
                imgMoviePoster.load(topMovie.poster) {
                    crossfade(true)
                    crossfade(2000)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val binding =
            ItemHomeTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.bindTopMovie(topMovies[position])
    }

    private class TopMoviesDiffUtilCallBack(
        val oldList: List<ResponseMovies.Data>,
        val newList: List<ResponseMovies.Data>
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

    fun submitList(newList: List<ResponseMovies.Data>) {
        val diffResult = DiffUtil.calculateDiff(TopMoviesDiffUtilCallBack(topMovies, newList))
        diffResult.dispatchUpdatesTo(this)
        topMovies.addAll(newList)
        topMovies.clear()
    }
}
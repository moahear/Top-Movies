package com.gamil.moahear.topmovies.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gamil.moahear.topmovies.databinding.ItemHomeLastMovieBinding
import com.gamil.moahear.topmovies.model.home.ResponseMovies
import javax.inject.Inject

class LastMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<LastMoviesAdapter.LastMoviesViewHolder>() {

    private val lastMovies = ArrayList<ResponseMovies.Data>()

    inner class LastMoviesViewHolder(private val binding: ItemHomeLastMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindTopMovie(lastMovie: ResponseMovies.Data) {
            binding.apply {
            txtMovieName.text=lastMovie.title
                txtMovieRate.text=lastMovie.imdbRating
                txtMovieCountry.text=lastMovie.country
                txtMovieYear.text=lastMovie.year
                imgMoviePoster.load(lastMovie.poster){
                    crossfade(true)
                    crossfade(800)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMoviesViewHolder {
        val binding =
            ItemHomeLastMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LastMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lastMovies.size
    }

    override fun onBindViewHolder(holder: LastMoviesViewHolder, position: Int) {
        holder.bindTopMovie(lastMovies[position])
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
        val diffResult = DiffUtil.calculateDiff(TopMoviesDiffUtilCallBack(lastMovies, newList))
        diffResult.dispatchUpdatesTo(this)
        lastMovies.clear()
        lastMovies.addAll(newList)
    }
}
package com.gamil.moahear.topmovies.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gamil.moahear.topmovies.databinding.ItemHomeLastMovieBinding
import com.gamil.moahear.topmovies.db.FavoriteMovieEntity
import com.gamil.moahear.topmovies.model.home.ResponseMovies
import javax.inject.Inject

class FavoriteMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<FavoriteMoviesAdapter.LastMoviesViewHolder>() {

    private val lastMovies = ArrayList<FavoriteMovieEntity>()

    inner class LastMoviesViewHolder(private val binding: ItemHomeLastMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindTopMovie(favoriteMovieEntity: FavoriteMovieEntity) {
            binding.apply {
                txtMovieName.text = favoriteMovieEntity.title
                txtMovieRate.text = favoriteMovieEntity.rate
                txtMovieCountry.text = favoriteMovieEntity.country
                txtMovieYear.text = favoriteMovieEntity.year
                imgMoviePoster.load(favoriteMovieEntity.poster) {
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
        val oldList: List<FavoriteMovieEntity>,
        val newList: List<FavoriteMovieEntity>
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

    fun submitList(newList: List<FavoriteMovieEntity>) {
        val diffResult = DiffUtil.calculateDiff(TopMoviesDiffUtilCallBack(lastMovies, newList))
        diffResult.dispatchUpdatesTo(this)
        lastMovies.clear()
        lastMovies.addAll(newList)
    }
}
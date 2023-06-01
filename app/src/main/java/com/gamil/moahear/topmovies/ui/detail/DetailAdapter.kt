package com.gamil.moahear.topmovies.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gamil.moahear.topmovies.databinding.ItemDetailsImagesBinding
import javax.inject.Inject

class DetailAdapter @Inject constructor() : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    inner class DetailViewHolder(private val binding: ItemDetailsImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPoster(poster: String) {
            binding.apply {
                imgDetail.load(poster) {
                    crossfade(true)
                    crossfade(800)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding =
            ItemDetailsImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindPoster(differ.currentList[position])
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

}
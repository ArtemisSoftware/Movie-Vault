package com.artemissoftware.movievault.ui.favourite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemissoftware.movievault.R
import com.artemissoftware.movievault.databinding.ItemMovieBinding
import com.artemissoftware.movievault.db.entities.Favorite
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FavoriteAdapter (private val listener: OnItemClickCallback) : ListAdapter<Favorite, FavoriteAdapter.FavoriteViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class FavoriteViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {

                    val position = adapterPosition

                    if(position != RecyclerView.NO_POSITION){
                        val movie = getItem(position)
                        listener.onItemClick(movie)
                    }

                }
            }
        }

        fun bind(favoriteMovie: Favorite) {
            with(binding) {

                Glide.with(itemView)
                    .load("${favoriteMovie.baseUrl}${favoriteMovie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivMoviePoster)
                tvMovieTitle.text = favoriteMovie.original_title
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(favoriteMovie: Favorite)
    }


    class DiffCallback : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite) =
            oldItem == newItem
    }
}
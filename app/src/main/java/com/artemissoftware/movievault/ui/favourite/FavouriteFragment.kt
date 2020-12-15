package com.artemissoftware.movievault.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.artemissoftware.movievault.R
import com.artemissoftware.movievault.data.Movie
import com.artemissoftware.movievault.databinding.FragmentFavouriteBinding
import com.artemissoftware.movievault.db.entities.Favorite
import com.artemissoftware.movievault.ui.favourite.adapters.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favourite), FavoriteAdapter.OnItemClickCallback{


    private val viewModel: FavouriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFavouriteBinding.bind(view)

        val adapter = FavoriteAdapter(this)

        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
        }

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.submitList(it)

        }

    }

    override fun onItemClick(favoriteMovie: Favorite) {
        val movie = Movie(
            favoriteMovie.id_movie,
            favoriteMovie.overview,
            favoriteMovie.poster_path,
            favoriteMovie.original_title
        )

        val action = FavouriteFragmentDirections.actionFavouriteFragmentToDetailsFragment(movie)
        findNavController().navigate(action)
    }
}
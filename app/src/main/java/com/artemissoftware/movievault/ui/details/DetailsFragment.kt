package com.artemissoftware.movievault.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.artemissoftware.movievault.R
import com.artemissoftware.movievault.data.Movie
import com.artemissoftware.movievault.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.*

import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details){

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {

            val movie = args.movie

            Glide.with(this@DetailsFragment)
                .load("${movie.baseUrl}${movie.poster_path}")
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable>{

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        progressBar.isVisible = false
                        tvDescription.isVisible = true
                        tvMovieTitle.isVisible = true
                        return false
                    }

                })
                .into(ivMoviePoster)

            var _isChecked = false

            //viewModel.checkMovie(movie.id)




            toggle_favorite.startJobOrCancel(movie)
//            CoroutineScope(Dispatchers.IO).launch{
//                val count = viewModel.checkMovie(movie.id)
//                withContext(Main){
//                    if (count > 0){
//                        toggleFavorite.isChecked = true
//                        _isChecked = true
//                    }else{
//                        toggleFavorite.isChecked = false
//                        _isChecked = false
//                    }
//                }
//            }


            tvDescription.text = movie.overview
            tvMovieTitle.text = movie.original_title

            toggleFavorite.setOnClickListener {
                _isChecked = !_isChecked
                if (_isChecked){
                    viewModel.addToFavorite(movie)
                } else{
                    viewModel.removeFromFavorite(movie.id)
                }
                toggleFavorite.isChecked = _isChecked
            }
        }

//option 2
//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.detailsEvent.collect{ event ->
//                when(event){
//                    is DetailsViewModel.DetailsEvent.ToggleFavorite ->{
//                        toggle_favorite.isChecked = event.result > 0
//                    }
//                }
//            }
//        }



    }

    //option 1
    fun ToggleButton.startJobOrCancel(movie: Movie){
            CoroutineScope(Dispatchers.IO).launch{
                val count = viewModel.checkMovie_(movie.id)
                withContext(Main){
                    this@startJobOrCancel.isChecked = count > 0
                }
            }
    }
}
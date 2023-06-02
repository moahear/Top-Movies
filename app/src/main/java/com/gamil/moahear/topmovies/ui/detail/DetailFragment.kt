package com.gamil.moahear.topmovies.ui.detail

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gamil.moahear.topmovies.R
import com.gamil.moahear.topmovies.databinding.FragmentDetailBinding
import com.gamil.moahear.topmovies.db.FavoriteMovieEntity
import com.gamil.moahear.topmovies.utils.initRecyclerView
import com.gamil.moahear.topmovies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var detailAdapter: DetailAdapter
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var favoriteMovieEntity: FavoriteMovieEntity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.getMovieDetails(args.movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            detailViewModel.movieDetails.observe(viewLifecycleOwner) { response ->
                detailAdapter.differ.submitList(response.images)
                imgBigPoster.load(response.poster)
                imgNormalPoster.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                txtMovieName.text = response.title
                txtMovieRate.text = response.imdbRating
                txtActorsInfo.text = response.actors
                txtMovieTime.text = response.runtime
                txtMovieDate.text = response.released
                txtSummaryInfo.text = response.plot
                rvImages.initRecyclerView(
                    adapter = detailAdapter,
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        false
                    )
                )

                //Click to toggle favorite
                imgFavorite.setOnClickListener {
                    favoriteMovieEntity.id = args.movieId
                    favoriteMovieEntity.title = response.title.toString()
                    favoriteMovieEntity.rate = response.rated.toString()
                    favoriteMovieEntity.poster = response.poster.toString()
                    favoriteMovieEntity.country = response.country.toString()
                    favoriteMovieEntity.year = response.year.toString()
                    detailViewModel.toggleFavorite(favoriteMovieEntity.id, favoriteMovieEntity)
                }


            }
            //Loading
            detailViewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    progressDetailMovies.visibility = View.VISIBLE
                    nestedScrollView.visibility = View.GONE
                } else {
                    progressDetailMovies.visibility = View.GONE
                    nestedScrollView.visibility = View.VISIBLE
                }
            }

            //Default favorite
            lifecycle.coroutineScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {
                    if (detailViewModel.existsInFavoriteMovies(args.movieId)) {
                        imgFavorite.setColorFilter(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.scarlet
                            ), PorterDuff.Mode.MULTIPLY
                        )
                    } else {
                        imgFavorite.setColorFilter(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.philippine_silver
                            ), PorterDuff.Mode.MULTIPLY
                        )
                    }
                }
            }
            //Change favorite with click
            detailViewModel.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    imgFavorite.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.scarlet
                        ), PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    imgFavorite.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.philippine_silver
                        ), PorterDuff.Mode.MULTIPLY
                    )
                }
            }
            //Back
            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}
package com.gamil.moahear.topmovies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gamil.moahear.topmovies.databinding.FragmentDetailBinding
import com.gamil.moahear.topmovies.utils.initRecyclerView
import com.gamil.moahear.topmovies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var detailAdapter: DetailAdapter
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
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
            detailViewModel.movieDetails.observe(viewLifecycleOwner) {
                detailAdapter.differ.submitList(it.images)
                imgBigPoster.load(it.poster)
                imgNormalPoster.load(it.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                txtMovieName.text = it.title
                txtMovieRate.text = it.imdbRating
                txtActorsInfo.text = it.actors
                txtMovieTime.text = it.runtime
                txtMovieDate.text = it.released
                txtSummaryInfo.text = it.plot
                rvImages.initRecyclerView(
                    adapter = detailAdapter,
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        false
                    )
                )
            }
            //Back
            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}
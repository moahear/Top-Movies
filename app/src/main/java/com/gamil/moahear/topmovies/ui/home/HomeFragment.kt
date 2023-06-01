package com.gamil.moahear.topmovies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.gamil.moahear.topmovies.databinding.FragmentHomeBinding
import com.gamil.moahear.topmovies.ui.home.adapter.GenresAdapter
import com.gamil.moahear.topmovies.ui.home.adapter.LastMoviesAdapter
import com.gamil.moahear.topmovies.ui.home.adapter.TopMoviesAdapter
import com.gamil.moahear.topmovies.utils.initRecyclerView
import com.gamil.moahear.topmovies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter
    @Inject
    lateinit var genresAdapter: GenresAdapter
    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val pagerSnapHelper by lazy {
        PagerSnapHelper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Call api getMovies
        homeViewModel.getTopMovies(3)
        homeViewModel.getGenres()
        homeViewModel.getLastMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //Get top movies
            homeViewModel.topMovies.observe(viewLifecycleOwner) {
                topMoviesAdapter.submitList(it)
                rvTopMovies.initRecyclerView(
                    adapter = topMoviesAdapter,
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        false
                    )
                )
                //Indicator
                pagerSnapHelper.attachToRecyclerView(rvTopMovies)
                indicatorTopMovies.attachToRecyclerView(rvTopMovies, pagerSnapHelper)
            }
            //Get genres
             homeViewModel.genres.observe(viewLifecycleOwner){
                 genresAdapter.submitList(it)
                 rvGenres.initRecyclerView(adapter = genresAdapter, layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false))
             }
            //Get last movies
            homeViewModel.lastMovies.observe(viewLifecycleOwner){
                lastMoviesAdapter.submitList(it.data)
                rvLastMovies.initRecyclerView(adapter = lastMoviesAdapter, layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false))
            }
            //Loading
            homeViewModel.isLoading.observe(viewLifecycleOwner){
                if (it) {
                    scrollView.visibility = View.GONE
                    progressBarCallMoviesApis.visibility = View.VISIBLE
                }
                else{
                    scrollView.visibility = View.VISIBLE
                    progressBarCallMoviesApis.visibility = View.GONE
                }
            }
        }
    }

}
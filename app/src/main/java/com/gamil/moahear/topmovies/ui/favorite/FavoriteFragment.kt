package com.gamil.moahear.topmovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamil.moahear.topmovies.databinding.FragmentFavoriteBinding
import com.gamil.moahear.topmovies.utils.initRecyclerView
import com.gamil.moahear.topmovies.viewmodel.FavoriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    @Inject
    lateinit var favoriteAdapter: FavoriteMoviesAdapter
    private val favoriteMoviesViewModel: FavoriteMoviesViewModel by viewModels()
    private lateinit var binding: FragmentFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            favoriteMoviesViewModel.getAllFavoriteMovies()
            //Show favorite movies
            favoriteMoviesViewModel.favoriteMovies.observe(viewLifecycleOwner) {
                favoriteAdapter.submitList(it)
                rvFavoriteMovies.initRecyclerView(
                    adapter = favoriteAdapter,
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.VERTICAL,
                        false
                    )
                )
            }
            //Click
            favoriteAdapter.onFavoriteMovieClick {
                findNavController().navigate(FavoriteFragmentDirections.actionToDetailFragment(it.id))
            }


            //Show or hide
            favoriteMoviesViewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    containerEmpty.visibility = View.VISIBLE
                    rvFavoriteMovies.visibility = View.GONE

                } else {
                    containerEmpty.visibility = View.GONE
                    rvFavoriteMovies.visibility = View.VISIBLE
                }
            }
        }
    }

}
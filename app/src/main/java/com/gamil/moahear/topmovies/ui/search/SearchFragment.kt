package com.gamil.moahear.topmovies.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamil.moahear.topmovies.databinding.FragmentSearchBinding
import com.gamil.moahear.topmovies.ui.home.adapter.LastMoviesAdapter
import com.gamil.moahear.topmovies.utils.initRecyclerView
import com.gamil.moahear.topmovies.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var searchAdapter: LastMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //Show search movies
            searchViewModel.movies.observe(viewLifecycleOwner) {
                Log.i("0000", "onViewCreated: ${it.data}")
                searchAdapter.submitList(it.data)
                Log.i("0000", "onViewCreated: ${searchAdapter.itemCount}")
                rvSeacrhMovies.initRecyclerView(
                    adapter = searchAdapter,
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.VERTICAL,
                        false
                    )
                )
            }
            //Loading
            searchViewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    progressSearchMovies.visibility = View.VISIBLE
                } else {
                    progressSearchMovies.visibility = View.GONE
                }
            }
            //EmptyList
            searchViewModel.isEmpty.observe(viewLifecycleOwner) {
                if (it) {
                    containerEmpty.visibility = View.VISIBLE
                    rvSeacrhMovies.visibility = View.GONE

                } else {
                    containerEmpty.visibility = View.GONE
                    rvSeacrhMovies.visibility = View.VISIBLE
                }
            }

            //Search
            edtSearch.addTextChangedListener {
                val search = it.toString()
                if (search.isNotEmpty()) {
                    searchViewModel.searchMovies(search)
                }
            }
        }
    }

}
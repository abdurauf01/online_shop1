package com.example.onlineshop.screen

import adapters.AdapterProducts
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.R
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import utils.PrefUtils


class FavouriteFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.progress.observe(requireActivity()) {
            view.swipe_favourite.isRefreshing = it
        }


    }

//    private fun loadData() {
//        viewModel.getProductsByIds(PrefUtils.getFavouritesList())
//        recycler_products_favourites.layoutManager = LinearLayoutManager(requireActivity())
//        val adapterProducts = AdapterProducts()
//        recycler_products_favourites.adapter = adapterProducts
//
//
//        viewModel.products.observe(viewLifecycleOwner) {
//            Toast.makeText(requireActivity(), "yedi", Toast.LENGTH_SHORT).show()
//            adapterProducts.submitItems(it)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Hawk.init(requireActivity()).build()
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    companion object {

        fun newInstance() = FavouriteFragment()

    }
}
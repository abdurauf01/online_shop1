package com.example.onlineshop.screen

import adapters.AdapterCarousel
import adapters.AdapterProducts
import adapters.CategoryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshop.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.math.abs

class HomeFragment : Fragment() {
    private lateinit var adapterCarousel: AdapterCarousel
    private lateinit var viewModel: MainViewModel
    private lateinit var adapterCategory: CategoryAdapter
    private lateinit var adapterProducts: AdapterProducts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapterCarousel = AdapterCarousel()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapterCategory = CategoryAdapter()
        adapterProducts = AdapterProducts()
        val productsRv = view.recycler_products
        productsRv.layoutManager = LinearLayoutManager(requireActivity())
        val pager = view.view_pager2

        view.recycler_category.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        swipe_home.setOnRefreshListener {
            loadData()
        }


        viewModel.errors.observe(requireActivity()) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.products.observe(requireActivity()) {
            adapterCarousel.submitItems(it)
            adapterProducts.submitItems(it)
        }

        viewModel.categories.observe(requireActivity()) {
            adapterCategory.submitItems(it)
        }

        viewModel.progress.observe(requireActivity()) {
            swipe_home.isRefreshing = it
        }



        productsRv.adapter = adapterProducts
        pager.adapter = adapterCarousel
        view.recycler_category.adapter = adapterCategory
        pager.offscreenPageLimit = 3
        pager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, pos ->
            val r = 1 - abs(pos)
            page.scaleY = 0.85f + r * 0.25f
        }
        pager.setPageTransformer(compositePageTransformer)
        loadData()

    }

    private fun loadData() {
        viewModel.getProducts()
        viewModel.getCategories()
    }

    companion object {
        fun newInstance() = HomeFragment()

    }
}
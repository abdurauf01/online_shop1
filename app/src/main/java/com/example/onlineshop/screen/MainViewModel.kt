package com.example.onlineshop.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.AllProducts
import network.repository.Repository

class MainViewModel : ViewModel() {


    val errors = MutableLiveData<String>()
    val products = MutableLiveData<AllProducts>()
    val categories = MutableLiveData<Categories>()
    val progress = MutableLiveData<Boolean>()


    fun getProducts() {
        Repository().getProducts(products, errors, progress)
    }


    fun getCategories() {
        Repository().getCategories(categories, errors)
    }

    fun getProductsByIds(ids: Int.Companion?){
        Repository().getProductsByIds(ids, products,errors)
    }




}
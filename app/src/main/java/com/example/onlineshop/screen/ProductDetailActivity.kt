package com.example.onlineshop.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_product_detail.*
import model.ProductsItem
import utils.Constants
import utils.PrefUtils

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var item: ProductsItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        Hawk.init(this).build()


        back_cardView.setOnClickListener {
            finish()
        }

        favourite_cardView.setOnClickListener {
            PrefUtils.setFav(item)
            changeImageState()

        }

        item = intent.getSerializableExtra(Constants.EXTRA_DATA) as ProductsItem

        tv_detail_description.text = item.description
        tv_detail_product_price.text = "$" + item.price.toString()
        tv_detail_title.text = item.title
        Glide.with(this).load(item.image).into(img_detail_product)
        changeImageState()
    }


    private fun changeImageState() {
        if (PrefUtils.checkFavouriteList(item)) {
            img_favourite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            img_favourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}


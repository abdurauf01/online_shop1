package adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.screen.ProductDetailActivity
import com.example.onlineshop.R
import kotlinx.android.synthetic.main.item_products.view.*
import model.ProductsItem
import utils.Constants

class AdapterProducts : RecyclerView.Adapter<AdapterProducts.MyHolder>() {
    private val data = ArrayList<ProductsItem>()

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(products: ProductsItem) {
            itemView.setOnClickListener {
                val intent = Intent(it.context, ProductDetailActivity::class.java)
                intent.putExtra(Constants.EXTRA_DATA, products)
                it.context.startActivity(intent)
            }
            itemView.tv_title_product.text = products.title
            itemView.tv_description_product.text = products.description
            itemView.tv_price_product.text = "$" + products.price.toString()
            Glide.with(itemView).load(products.image).into(itemView.img_products)
        }

    }

    fun submitItems(list: ArrayList<ProductsItem>) {
        val diff = MyCallBack(data, list)
        val diffUtil = DiffUtil.calculateDiff(diff)
        data.clear()
        data.addAll(list)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.makeramen.roundedimageview.RoundedImageView
import model.ProductsItem

class AdapterCarousel : RecyclerView.Adapter<AdapterCarousel.MyHolder>() {
    private val data = ArrayList<ProductsItem>()

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val img: RoundedImageView = itemView.findViewById(R.id.img_carousel)
        fun bind(productsItem: ProductsItem) {
            Glide.with(img).load(productsItem.image).into(img)
        }
    }

    fun submitItems(list: List<ProductsItem>) {
        val callBack = MyCallBack(data, list)
        val diffUtil = DiffUtil.calculateDiff(callBack)
        data.clear()
        data.addAll(list)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
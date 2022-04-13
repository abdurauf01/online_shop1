package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import kotlinx.android.synthetic.main.item_cart.view.*
import model.CartItems

class AdapterCart(var data: ArrayList<CartItems>) : RecyclerView.Adapter<AdapterCart.ItemHolder>() {
    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(cartModel: CartItems) {
            itemView.img_cart_minus.setImageResource(R.drawable.ic_minus_svgrepo_com__1_)
            itemView.img_cart_plus.setImageResource(R.drawable.ic_icons8_plus__)
            itemView.tv_cart_count.text = cartModel.products[adapterPosition].productId.toString()
            itemView.tv_price_cart.text

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
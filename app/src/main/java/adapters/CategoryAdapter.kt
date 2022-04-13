package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.Myholder>() {
    private val data = ArrayList<String>()
    private var listener: ((String) -> Unit?)? = null

    inner class Myholder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var textback: TextView
        fun bind(s: String) {
            textback = itemView.tv_category
            textback.text = s
            listener?.invoke(s)
            var t = false

        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return Myholder(view)
    }

    fun submitItems(list: ArrayList<String>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Myholder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
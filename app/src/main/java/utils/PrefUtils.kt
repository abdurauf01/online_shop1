package utils

import com.orhanobut.hawk.Hawk
import model.ProductsItem

object PrefUtils {


    fun setFav(item: ProductsItem) {

        val items = Hawk.get(Constants.PREF_FAVOURITES, 0)


        if (item.id != items)
            Hawk.put(Constants.PREF_FAVOURITES, items)
    }

    fun getFavouritesList(): Int.Companion? {
        return Hawk.get(Constants.PREF_FAVOURITES, Int)
    }

    fun checkFavouriteList(item: ProductsItem): Boolean {
        val items = Hawk.get(Constants.PREF_FAVOURITES, 0)
        return items != null && item.id == items
    }
}
package network

import com.example.onlineshop.screen.Categories
import io.reactivex.rxjava3.core.Observable
import model.AllProducts
import model.GetProductsByIdsRequest
import model.ProductsItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    //https://fakestoreapi.com/products
    @GET("products")
    fun getProducts(): Observable<AllProducts>

    //https://fakestoreapi.com/products/categories
    @GET("products/categories")
    fun getCategories(): Observable<Categories>

    //https://fakestoreapi.com/
    @POST("products")
    fun getProductByCategory(@Body request: Int.Companion?): Observable<AllProducts>
}
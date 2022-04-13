package network.repository

import androidx.lifecycle.MutableLiveData
import com.example.onlineshop.screen.Categories
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import model.AllProducts
import network.NetworkManager

class Repository {

    private val compositeDisposable = CompositeDisposable()
    fun getProducts(
        products: MutableLiveData<AllProducts>,
        errors: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>
    ) {
        progress.value = false
        compositeDisposable.add(
            NetworkManager.getApi().getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<AllProducts>() {
                    override fun onNext(t: AllProducts) {
                        if (t.size > 0) {
                            products.value = t
                            progress.value = false
                        }
                    }

                    override fun onError(e: Throwable) {
                        errors.value = e.localizedMessage
                        progress.value = true
                    }

                    override fun onComplete() {
                    }
                })
        )


    }

    fun getCategories(categories: MutableLiveData<Categories>, errors: MutableLiveData<String>) {
        compositeDisposable.add(
            NetworkManager.getApi().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Categories>() {

                    override fun onNext(t: Categories) {
                        if (t.size > 0) {
                            categories.value = t

                        }
                    }

                    override fun onError(e: Throwable) {
                        errors.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                })
        )
    }


    fun getProductsByIds(
        ids: Int.Companion?,
        products: MutableLiveData<AllProducts>,
        errors: MutableLiveData<String>
    ) {
        compositeDisposable.add(
            NetworkManager.getApi().getProductByCategory(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<AllProducts>() {
                    override fun onNext(t: AllProducts) {
                        products.value = t
                    }

                    override fun onError(e: Throwable) {
                        errors.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )


    }
}
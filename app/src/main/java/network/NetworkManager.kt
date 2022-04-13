package network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import utils.Constants

object NetworkManager {
    private var retrofit: Retrofit? = null
    private var api: Api? = null

    fun getApi(): Api {
        if (api == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            api = retrofit!!.create(Api::class.java)
        }

        return api!!
    }
}
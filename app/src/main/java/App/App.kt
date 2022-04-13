package App

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.orhanobut.hawk.Hawk

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Hawk.init(this).build()
    }

}
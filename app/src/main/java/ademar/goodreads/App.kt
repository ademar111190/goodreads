package ademar.goodreads

import ademar.goodreads.core.initializer.AppInitializer
import ademar.goodreads.core.initializer.DatabaseInitializer
import ademar.goodreads.core.initializer.GolInitializer
import ademar.goodreads.core.initializer.InitializerManager
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        App.instance = this
        InitializerManager.start(
                AppInitializer(),
                GolInitializer(),
                DatabaseInitializer())
    }

    companion object {
        var instance: Application? = null
            set
    }

}

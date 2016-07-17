package ademar.goodreads

import ademar.goodreads.core.initializer.*
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        App.instance = this
        InitializerManager.start(
                AppInitializer(),
                GolInitializer(),
                RecentsInitializer(),
                DatabaseInitializer())
    }

    companion object {
        var instance: Application? = null
            set
    }

}

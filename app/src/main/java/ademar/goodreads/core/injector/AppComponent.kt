package ademar.goodreads.core.injector

import ademar.goodreads.core.initializer.AppInitializer
import ademar.goodreads.core.manager.SearchManager
import ademar.goodreads.ui.common.activity.StartActivity
import ademar.goodreads.ui.search.activity.SearchActivity
import ademar.goodreads.ui.search.adapter.SearchAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    // Activity

    fun inject(o: SearchActivity)

    fun inject(o: StartActivity)

    // Adapter

    fun inject(o: SearchAdapter)

    // Initializer

    fun inject(o: AppInitializer)

    // Manager

    fun inject(o: SearchManager)

    object Initialize {

        private var sInstance: AppComponent? = null

        fun get(): AppComponent {
            if (sInstance == null) {
                set(DaggerAppComponent.create())
            }
            return sInstance!!
        }

        fun set(component: AppComponent) {
            sInstance = component
        }

    }

}

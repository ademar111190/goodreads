package ademar.goodreads.core.initializer

import ademar.goodreads.core.injector.AppComponent
import ademar.goodreads.core.manager.SearchManager
import android.content.Context
import javax.inject.Inject

class AppInitializer : Initializer.SynchronousInitializer() {

    @Inject lateinit var searchManager: SearchManager

    override fun start(context: Context) {
        val component = AppComponent.Initialize.get()
        component.inject(this)
        component.inject(searchManager)
    }

}

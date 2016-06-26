package ademar.goodreads.core.initializer

import ademar.goodreads.core.injector.AppComponent
import android.content.Context

class AppInitializer : Initializer.SynchronousInitializer() {

    override fun start(context: Context) {
        AppComponent.Initialize.get()
    }

}


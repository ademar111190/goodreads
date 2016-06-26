package ademar.goodreads.core.initializer

import android.content.Context

import mobi.porquenao.gol.Gol

class GolInitializer : Initializer.SynchronousInitializer() {

    override fun start(context: Context) {
        Gol.getDefault().tag = "Goodreads"
    }

}

package ademar.goodreads.core.initializer

import ademar.goodreads.App
import mobi.porquenao.gol.Gol
import rx.Observable
import java.util.*

class InitializerManager private constructor(observables: List<Observable<Void>>) {

    private val mObservable: Observable<Void>

    init {
        mObservable = Observable.merge(observables).cache()
        mObservable.subscribe()
    }

    fun onFinish(onFinishListener: OnFinishListener) {
        mObservable.subscribe({ onFinishListener.finish() }) { throwable -> Gol.getDefault().error(throwable) }
    }

    interface OnFinishListener {

        fun finish()

    }

    companion object {

        var instance: InitializerManager? = null
            private set

        @Synchronized
        fun start(vararg initializers: Initializer) {
            instance = InitializerManager(object : ArrayList<Observable<Void>>() {
                init {
                    for (initializer in initializers) {
                        add(initializer.getObservable(App.instance!!))
                    }
                }
            })
        }

        @Synchronized
        fun stop() {
            instance = null
        }

    }

}

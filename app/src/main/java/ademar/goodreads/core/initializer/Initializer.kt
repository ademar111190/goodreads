package ademar.goodreads.core.initializer

import android.content.Context
import android.os.AsyncTask
import mobi.porquenao.gol.Gol

import rx.Observable
import rx.schedulers.Schedulers

interface Initializer {

    fun getObservable(context: Context): Observable<Void>

    abstract class SynchronousInitializer : Initializer {

        abstract fun start(context: Context)

        override fun getObservable(context: Context): Observable<Void> {
            return Observable.create(Observable.OnSubscribe<java.lang.Void> { subscriber ->
                start(context)
                if (!subscriber.isUnsubscribed) {
                    subscriber.onNext(null)
                    subscriber.onCompleted()
                }
            }).onErrorResumeNext { throwable ->
                Gol.getDefault().error(Exception("Initializer ${this@SynchronousInitializer.javaClass.simpleName} has suffered the error: $throwable"))
                Observable.empty<Void>()
            }.cache()
        }

    }

    abstract class AsynchronousInitializer : SynchronousInitializer() {

        override fun getObservable(context: Context): Observable<Void> {
            return super.getObservable(context).subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
        }

    }

}

package ademar.goodreads.core.service

import mobi.porquenao.gol.Gol
import retrofit2.Response
import rx.Observable
import rx.Subscription

class Observatory<T, U : Response<T>>(val observer: Observable<U>) {

    private lateinit var success: (T) -> Unit
    private lateinit var error: (Throwable) -> Unit

    fun onSuccess(listener: (T) -> Unit): Observatory<T, U> {
        success = listener
        return this
    }

    fun onError(listener: (Throwable) -> Unit): Observatory<T, U> {
        error = listener
        return this
    }

    fun subscribe(): Subscription {
        return observer.subscribe({ item ->
            if (item.isSuccessful) {
                val body = item.body()
                if (body != null) {
                    success.invoke(body)
                } else {
                    error.invoke(Exception("No body"))
                }
            } else {
                error.invoke(Exception(item.raw().toString()))
            }
        }, { throwable ->
            Gol.getDefault().error(throwable)
            error.invoke(throwable)
        })
    }

}
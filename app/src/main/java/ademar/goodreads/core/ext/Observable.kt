package ademar.goodreads.core.ext

import ademar.goodreads.core.service.Observatory
import retrofit2.Response
import rx.Observable

fun <T, U : Response<T>> Observable<U>.observatory() = Observatory(this)

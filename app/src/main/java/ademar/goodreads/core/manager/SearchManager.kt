package ademar.goodreads.core.manager

import ademar.goodreads.core.ext.observatory
import ademar.goodreads.core.model.Work
import ademar.goodreads.core.service.Services
import android.util.LongSparseArray
import mobi.porquenao.gol.Gol
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io
import rx.subjects.PublishSubject
import java.util.*
import javax.inject.Inject

open class SearchManager {

    @Inject lateinit var services: Services

    val works = HashSet<Work>()
    val worksMap = LongSparseArray<Work>()
    val publish = PublishSubject.create<Work>();

    fun search(query: CharSequence, listener: (Boolean) -> Unit) {
        services.searchService
                .search(query)
                .subscribeOn(io())
                .observeOn(mainThread())
                .unsubscribeOn(io())
                .observatory()
                .onSuccess { searchResponse ->
                    searchResponse.search.works.forEach { work ->
                        worksMap.put(work.id, work)
                        works.add(work)
                        publish.onNext(work)
                    }
                    listener.invoke(true)
                }
                .onError { throwable ->
                    Gol.getDefault().error(throwable)
                    listener.invoke(false)
                }
                .subscribe()
    }

}

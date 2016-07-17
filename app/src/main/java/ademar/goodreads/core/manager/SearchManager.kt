package ademar.goodreads.core.manager

import ademar.goodreads.core.ext.observatory
import ademar.goodreads.core.injector.AppComponent
import ademar.goodreads.core.model.Work
import ademar.goodreads.core.service.Services
import android.util.LongSparseArray
import mobi.porquenao.gol.Gol
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io
import rx.subjects.PublishSubject
import java.util.*
import javax.inject.Inject

class SearchManager {

    @Inject lateinit var services: Services

    val works = ArrayList<Work>()
    val worksMap = LongSparseArray<Work>()
    val publish: PublishSubject<Work> = PublishSubject.create<Work>()

    var total = 0
    var page = 1
    var query: CharSequence = ""
    private var subscription: Subscription? = null

    init {
        AppComponent.Initialize.get().inject(this)
    }

    fun clear() {
        subscription?.unsubscribe()
        worksMap.clear()
        works.clear()
        total = 0
        page = 1
        query = ""
    }

    fun search(query: CharSequence, listener: (Boolean) -> Unit) {
        clear()
        this.query = query.trim()

        subscription = services.searchService
                .search(this.query)
                .subscribeOn(io())
                .observeOn(mainThread())
                .unsubscribeOn(io())
                .observatory()
                .onSuccess { searchResponse ->
                    val search = searchResponse.search
                    total = search.total
                    page++

                    search.works.forEach { work ->
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

    fun next(listener: (Boolean) -> Unit) {
        subscription = services.searchService
                .search(query, page)
                .subscribeOn(io())
                .observeOn(mainThread())
                .unsubscribeOn(io())
                .observatory()
                .onSuccess { searchResponse ->
                    val search = searchResponse.search
                    page++
                    search.works.forEach { work ->
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

package ademar.goodreads.ui.search.adapter

import ademar.goodreads.core.injector.AppComponent
import ademar.goodreads.core.manager.SearchManager
import ademar.goodreads.ui.common.holder.WorkHolder
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io
import javax.inject.Inject

class SearchAdapter : RecyclerView.Adapter<WorkHolder> {

    @Inject lateinit var searchManager: SearchManager

    constructor() {
        AppComponent.Initialize.get().inject(this)

        setHasStableIds(true)

        searchManager.publish
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribe({ work ->
                    notifyDataSetChanged()
                }, {});
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WorkHolder? {
        return WorkHolder(parent)
    }

    override fun getItemCount(): Int {
        return searchManager.works.size
    }

    override fun getItemId(position: Int): Long {
        return searchManager.works.elementAt(position).id
    }

    override fun onBindViewHolder(holder: WorkHolder?, position: Int) {
        holder?.bind(searchManager.works.elementAt(position))
    }

}
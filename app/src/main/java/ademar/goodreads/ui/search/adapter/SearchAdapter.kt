package ademar.goodreads.ui.search.adapter

import ademar.goodreads.core.manager.SearchManager
import ademar.goodreads.ui.common.holder.BaseHolder
import ademar.goodreads.ui.common.holder.LoadHolder
import ademar.goodreads.ui.common.holder.WorkHolder
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup

class SearchAdapter(val searchManager: SearchManager) : RecyclerView.Adapter<BaseHolder<*>>() {

    private val TYPE_ITEM = 1
    private val TYPE_LOAD = 2

    init {
        setHasStableIds(true)
        searchManager.publish.subscribe({ notifyDataSetChanged() }, {})
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == searchManager.works.size) TYPE_LOAD else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<*> {
        return if (viewType == TYPE_ITEM) WorkHolder(parent) else LoadHolder(parent)
    }

    override fun getItemCount(): Int {
        val size = searchManager.works.size
        return size + (if (searchManager.total > size) 1 else 0)
    }

    override fun getItemId(position: Int): Long {
        return if (position == searchManager.works.size) -1 else searchManager.works[position].id
    }

    override fun onBindViewHolder(holder: BaseHolder<*>, position: Int) {
        if (holder is WorkHolder) {
            holder.bind(searchManager.works[position])
        }
        if (holder is LoadHolder) {
            val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            layoutParams.isFullSpan = true
            holder.itemView.visibility = View.VISIBLE
            searchManager.next { success ->
                if (!success) {
                    holder.itemView.visibility = View.GONE
                }
            }
        }
    }

}

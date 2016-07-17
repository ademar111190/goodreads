package ademar.goodreads.ui.common.holder

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KProperty

abstract class BaseHolder<T>(parent: ViewGroup?, @LayoutRes layout: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent?.context).inflate(layout, parent, false)) {

    abstract fun bind(item: T)

    class BindView<V : View>(@IdRes val id: Int) {

        @Suppress("UNCHECKED_CAST")
        operator fun getValue(thisRef: BaseHolder<*>, property: KProperty<*>): V {
            return thisRef.itemView.findViewById(id) as V
        }

        operator fun setValue(thisRef: BaseHolder<*>, property: KProperty<*>, value: V) {
        }

    }


}

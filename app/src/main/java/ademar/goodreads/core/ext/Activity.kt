package ademar.goodreads.core.ext

import android.app.Activity
import android.support.annotation.DimenRes
import android.support.annotation.IdRes
import android.view.View
import kotlin.reflect.KProperty

class BindDimen(@DimenRes val id: Int) {

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Activity, property: KProperty<*>): Float {
        return thisRef.resources.getDimension(id)
    }

    operator fun setValue(thisRef: Activity, property: KProperty<*>, value: Float) {
    }

}

class BindView<V : View>(@IdRes val id: Int) {

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Activity, property: KProperty<*>): V {
        return thisRef.findViewById(id) as V
    }

    operator fun setValue(thisRef: Activity, property: KProperty<*>, value: V) {
    }

}

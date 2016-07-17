package ademar.goodreads.core.ext

import android.view.View
import android.view.ViewTreeObserver

fun View.atFirstGlobalLayout(listener: () -> Unit): Unit {
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            listener.invoke()
        }
    })
}

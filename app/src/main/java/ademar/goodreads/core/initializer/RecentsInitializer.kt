package ademar.goodreads.core.initializer

import ademar.goodreads.R
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import androidx.core.content.ContextCompat
import mobi.porquenao.recents.Recents

class RecentsInitializer : Initializer.AsynchronousInitializer() {

    override fun start(context: Context) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_recents) as VectorDrawable
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        Recents.icon(bitmap)
                .color(ContextCompat.getColor(context, R.color.app_primary_dark))
                .on(context as Application)
    }

}
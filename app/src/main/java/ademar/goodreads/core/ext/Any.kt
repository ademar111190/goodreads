package ademar.goodreads.core.ext

import ademar.goodreads.App
import android.app.Application

fun Any.app(): Application = App.instance!!

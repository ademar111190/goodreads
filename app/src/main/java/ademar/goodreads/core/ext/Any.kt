package ademar.goodreads.core.ext

import ademar.goodreads.App
import android.app.Application
import android.support.annotation.StringRes

fun Any.app(): Application = App.instance!!

fun Any.findString(@StringRes string: Int, vararg args: Any): String = this.app().getString(string, args)
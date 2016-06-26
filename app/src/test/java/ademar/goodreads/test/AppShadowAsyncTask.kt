package ademar.goodreads.test

import android.os.AsyncTask

import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import org.robolectric.shadows.ShadowAsyncTask

import java.util.concurrent.Executor

@Implements(AsyncTask::class)
class AppShadowAsyncTask<Params, Progress, Result> : ShadowAsyncTask<Params, Progress, Result>() {

    @RealObject private val realAsyncTask: AsyncTask<Params, Progress, Result>? = null

    @SafeVarargs
    @Implementation
    override fun executeOnExecutor(executor: Executor, vararg params: Params): AsyncTask<Params, Progress, Result> {
        return super.execute(*params)
    }

}

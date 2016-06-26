package ademar.goodreads.ui.activity

import ademar.goodreads.core.injector.AppComponent
import ademar.goodreads.core.service.Services
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mobi.porquenao.gol.Gol
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class StartActivity : AppCompatActivity() {

    @Inject lateinit var services: Services

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppComponent.Initialize.get().inject(this)

        services.searchService
                .search("Atlas Shrugged")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        Gol.l("Response success")
                        Gol.l(response.body())
                    } else {
                        Gol.l("Response error, raw:", response.raw())
                    }
                }, { throwable ->
                    Gol.l("Response error", throwable)
                })
    }

}

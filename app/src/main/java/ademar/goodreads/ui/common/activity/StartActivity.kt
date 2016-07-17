package ademar.goodreads.ui.common.activity

import ademar.goodreads.core.service.Services
import android.os.Bundle
import javax.inject.Inject

class StartActivity : BaseActivity() {

    @Inject lateinit var services: Services

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.newIntent())
        finish()
    }

}

package ademar.goodreads.ui.common.activity

import ademar.goodreads.R
import ademar.goodreads.core.ext.app
import ademar.goodreads.core.ext.findString
import ademar.goodreads.core.service.Services
import ademar.goodreads.ui.search.activity.SearchActivity
import android.content.Intent
import android.os.Bundle
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var services: Services

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        toolbar.title = findString(R.string.main_label)
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener { menu ->
            if (menu.itemId == R.id.search) {
                startActivity(SearchActivity.newIntent())
            }
            true
        }
    }

    companion object {

        fun newIntent(): Intent {
            return Intent(app(), MainActivity::class.java)
        }

    }

}

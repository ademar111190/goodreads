package ademar.goodreads.ui.common.activity

import ademar.goodreads.R
import ademar.goodreads.core.ext.BindView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    val toolbar: Toolbar by BindView(R.id.toolbar)

}
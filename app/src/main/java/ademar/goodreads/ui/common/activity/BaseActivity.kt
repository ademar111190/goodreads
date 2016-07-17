package ademar.goodreads.ui.common.activity

import ademar.goodreads.R
import ademar.goodreads.core.ext.BindView
import android.content.Intent
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    val toolbar: Toolbar by BindView(R.id.toolbar)

    fun navigateUp(intent: Intent) {
        if (NavUtils.shouldUpRecreateTask(this, intent)) {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(intent)
                    .startActivities()
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            NavUtils.navigateUpTo(this, intent)
        }
    }

}
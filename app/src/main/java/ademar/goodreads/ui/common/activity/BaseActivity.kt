package ademar.goodreads.ui.common.activity

import ademar.goodreads.R
import ademar.goodreads.core.ext.BindView
import android.content.Intent
import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

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
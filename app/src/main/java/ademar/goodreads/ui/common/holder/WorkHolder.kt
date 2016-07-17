package ademar.goodreads.ui.common.holder

import ademar.goodreads.R
import ademar.goodreads.core.ext.app
import ademar.goodreads.core.model.Work
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class WorkHolder(parent: ViewGroup?) : BaseHolder<Work>(parent, R.layout.work_item) {

    val text: TextView by BindView(R.id.text)

    override fun bind(item: Work) {
        text.text = item.bestBook.title
        text.setOnClickListener {
            Toast.makeText(app(), text.text, Toast.LENGTH_SHORT).show()
        }
    }

}

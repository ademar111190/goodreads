package ademar.goodreads.ui.common.holder

import ademar.goodreads.R
import ademar.goodreads.core.ext.app
import ademar.goodreads.core.model.Work
import androidx.cardview.widget.CardView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import mobi.porquenao.gol.Gol

class WorkHolder(parent: ViewGroup?) : BaseHolder<Work>(parent, R.layout.work_item) {

    val card: CardView by BindView(R.id.card)
    val image: ImageView by BindView(R.id.image)
    val text: TextView by BindView(R.id.text)

    override fun bind(item: Work) {
        val book = item.bestBook
        text.text = book.title
        card.setOnClickListener {
            Gol.l(book.title)
        }
        Picasso.with(app())
                .load(book.image)
                .into(image)
    }

}

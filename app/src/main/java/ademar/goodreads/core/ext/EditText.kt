package ademar.goodreads.core.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(listener: (text: CharSequence) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            listener.invoke(text ?: "")
        }

    })
}

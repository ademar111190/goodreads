package ademar.goodreads.core.ext

import ademar.goodreads.test.BaseTestCase
import ademar.goodreads.test.EDIT_TEXT_TEXT
import ademar.goodreads.test.When
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.EditText
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Matchers.any
import org.mockito.Matchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import java.util.*

class EditTextTest : BaseTestCase() {

    @Mock lateinit var editText: EditText

    @Test
    fun testOnTextChanged() {
        val watchers = ArrayList<TextWatcher>()

        When(editText.addTextChangedListener(any())).thenAnswer { invocation ->
            watchers.add(invocation.arguments[0] as TextWatcher)
        }

        When(editText.setText(anyString())).then {
            watchers.forEach { watcher ->
                watcher.beforeTextChanged(EDIT_TEXT_TEXT, 0, 0, 0)
                watcher.onTextChanged(EDIT_TEXT_TEXT, 0, 0, 0)
                watcher.afterTextChanged(SpannableStringBuilder(EDIT_TEXT_TEXT))
            }
        }

        var calledCount = 0
        editText.onTextChanged {
            calledCount++
        }

        editText.text = SpannableStringBuilder(EDIT_TEXT_TEXT)

        assertThat(calledCount).isEqualTo(1)

        verify(editText).addTextChangedListener(any())
    }

}
